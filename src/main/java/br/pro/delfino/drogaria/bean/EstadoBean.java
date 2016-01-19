package br.pro.delfino.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogaria.dao.EstadoDAO;
import br.pro.delfino.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable{
	private Estado estado;
	private List<Estado> estados;
	
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

	//@PostConstruct: É chamado após o contrutor default da class, então o manageBean é criado 
	//automáticamente será chamado o método listar()
	@PostConstruct
	public void listar(){
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os registros, erro: "+ erro);
			erro.printStackTrace();
		}
	}	
	
	public void novo(){
		estado = new Estado();
	}
	
	public void salvar(){
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);
			
			novo();
			
			//Após inserir é necessário atualizar a listagem (ManageBean)
			estados = estadoDAO.listar();
			
			Messages.addGlobalInfo("Registro salvo com sucesso.");	
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o registro, erro: "+ erro);
			erro.printStackTrace(); //Pilha de execução no catina.out
		}
	}
	
	public void excluir(ActionEvent evento){
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estado);
			
			//Após excluir regarrega a listagem.
			estados = estadoDAO.listar();
			
			Messages.addGlobalInfo("Registro removido com sucesso.");	
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao excluir o registro, erro: "+ erro);
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		Messages.addGlobalInfo("Estado: " + estado.getNome() + " - "+estado.getSigla());
	}
}
