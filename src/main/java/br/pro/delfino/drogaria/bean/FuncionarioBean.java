package br.pro.delfino.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogaria.dao.FuncionarioDAO;
import br.pro.delfino.drogaria.dao.PessoaDAO;
import br.pro.delfino.drogaria.domain.Funcionario;
import br.pro.delfino.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable{
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private List<Pessoa> pessoas;
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	@PostConstruct
	public void listar(){
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios =  funcionarioDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os registros, erro: "+ erro);
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		try {
			funcionario = new Funcionario();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao inserir um novo Funcionário, erro: "+ erro);
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.merge(funcionario);
			
			funcionario = new Funcionario();
			funcionarios = funcionarioDAO.listar();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
			Messages.addGlobalInfo("Registro salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o registro, erro: "+ erro);
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario);
			
			funcionarios = funcionarioDAO.listar();
			
			Messages.addGlobalInfo("Registro removido com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao excluir o registro, erro: "+ erro);
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao editar o registro, erro: "+ erro);
			erro.printStackTrace();
		}
	}	
}
