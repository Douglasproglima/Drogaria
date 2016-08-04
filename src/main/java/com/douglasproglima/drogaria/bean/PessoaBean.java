package com.douglasproglima.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.douglasproglima.drogaria.dao.CidadeDAO;
import com.douglasproglima.drogaria.dao.EstadoDAO;
import com.douglasproglima.drogaria.dao.PessoaDAO;
import com.douglasproglima.drogaria.domain.Cidade;
import com.douglasproglima.drogaria.domain.Estado;
import com.douglasproglima.drogaria.domain.Pessoa;
import com.google.gson.Gson;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable{
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	
	private Estado estado;
	private List<Estado> estados;
	private List<Cidade> cidades;
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}
	
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	public List<Cidade> getCidades() {
		return cidades;
	}
	
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	@PostConstruct
	public void listar(){
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os registros, erro: "+ erro);
			erro.printStackTrace();
		}		
	}
	
	//Método que utiliza o WebServices ao invés do DAO
	public void novo(){
		try {
			pessoa = new Pessoa();
			estado =  new Estado();
			
			//Criando o cliente
			Client cliente = ClientBuilder.newClient();
			//Caminho do WebServices
			WebTarget caminho = cliente.target("http://127.0.0.1:8080/Drogaria/rest/estado");
			//Faz a chamada do get() e pega o resultado em JSON
			String json = caminho.request().get(String.class);
			
			//Converte o JSON para vector utilizando a API do google Gson
			Gson gson = new Gson();
			//Faz a conversão para um vector do objeto Estado[]
			Estado[] vetor = gson.fromJson(json, Estado[].class);
			
			//Por ultimo converte o vector um ArrayList de estados
			estados = Arrays.asList(vetor);
			
			cidades = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao inserir uma nova pessoa, erro: "+ erro);
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);
			
			pessoa = new Pessoa();
			pessoas = pessoaDAO.listar();
			
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();
			
			novo();
			
			Messages.addGlobalInfo("Registro salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o registro, erro: "+ erro);
			erro.printStackTrace();
		}		
	}
	
	public void excluir(ActionEvent evento){
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);
			
			pessoas = pessoaDAO.listar();
			
			Messages.addGlobalInfo("Registro removido com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao excluir o registro, erro: "+ erro);
			erro.printStackTrace();
		}		
	}
	
	public void editar(ActionEvent evento){
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			
			EstadoDAO estadoDAO =  new EstadoDAO();
			estados =  estadoDAO.listar();
			
			estado = pessoa.getCidade().getEstado();
			
			//Instânciando o estado para mostrar o campo list do botão novo.
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar("nome");	
			
			/*
			 * CidadeDAO cidadeDAO = new CidadeDAO();
			 * cidades = cidadeDAO.buscaPorEstado(estado.getCodigo());
			 * */
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao selecionar o registro, erro: "+ erro);
			erro.printStackTrace();
		}		
	}
	
	//Método para popular o combo de cidades de acordo com o estado selecionado.
	public void popular(){
		try{
			if(estado != null){
				//Criando o cliente
				Client cliente = ClientBuilder.newClient();
				
				//Caminho do WebServices passando o código do estado como parâmetro para RESTful
				WebTarget caminho = cliente.target("http://127.0.0.1:8080/Drogaria/rest/cidade/{estadoCodigo}")
										   .resolveTemplate("estadoCodigo", estado.getCodigo());
				
				//Faz a chamada do get() e pega o resultado em JSON
				String json = caminho.request().get(String.class);
				
				//Converte o JSON para vector utilizando a API do google Gson
				Gson gson = new Gson();
				
				//Faz a conversão para um vector do objeto Cidade[]
				Cidade[] vetor = gson.fromJson(json, Cidade[].class);
				
				//Por ultimo converte o vector um ArrayList de cidades
				cidades = Arrays.asList(vetor);
			}else{
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao filtrar as cidades, erro: "+ erro);
			erro.printStackTrace();
		}	
	}
}
