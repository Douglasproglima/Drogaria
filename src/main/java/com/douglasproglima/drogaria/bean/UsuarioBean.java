package com.douglasproglima.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import com.douglasproglima.drogaria.dao.PessoaDAO;
import com.douglasproglima.drogaria.dao.UsuarioDAO;
import com.douglasproglima.drogaria.domain.Pessoa;
import com.douglasproglima.drogaria.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{
	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
			
		} catch (RuntimeException erro) {
			//Messages.addGlobalError("Erro ao listar os registros, erro: "+ erro);
			Messages.addGlobalError(Faces.getResourceBundle("varMsg").getString("msgErroListar") +" "+ erro);
			
			erro.printStackTrace();
		}
	}
	
	public void novo(){
		try {
			usuario = new Usuario();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException erro) {
			//Messages.addGlobalError("Erro ao inserir um novo Usuário, erro: "+ erro);
			Messages.addGlobalError(Faces.getResourceBundle("varMsg").getString("msgErroInserir") +" "+ erro);
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);
			
			usuario = new Usuario();
			usuarios = usuarioDAO.listar();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
			//Messages.addGlobalInfo("Registro salvo com sucesso.");
			Messages.addGlobalInfo(Faces.getResourceBundle("varMsg").getString("msgOkSalvar"));
		} catch (RuntimeException erro) {
			//Messages.addGlobalError("Erro ao salvar o registro, erro: "+ erro);
			Messages.addGlobalError(Faces.getResourceBundle("varMsg").getString("msgErroSalvar") +" "+ erro);
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);
			
			usuarios = usuarioDAO.listar();
			
			//Messages.addGlobalInfo("Registro removido com sucesso.");
			Messages.addGlobalInfo(Faces.getResourceBundle("varMsg").getString("msgOkExcluir"));
		} catch (RuntimeException erro) {
			//Messages.addGlobalError("Erro ao excluir o registro, erro: "+ erro);
			Messages.addGlobalError(Faces.getResourceBundle("varMsg").getString("msgErroExcluir") +" "+ erro);
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException erro) {
			//Messages.addGlobalError("Erro ao editar o registro, erro: "+ erro);
			Messages.addGlobalError(Faces.getResourceBundle("varMsg").getString("msgErroEditar") +" "+ erro);
			erro.printStackTrace();
		}
	}	
}
