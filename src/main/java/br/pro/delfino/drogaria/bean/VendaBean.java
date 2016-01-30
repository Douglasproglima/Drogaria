package br.pro.delfino.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogaria.dao.ProdutoDAO;
import br.pro.delfino.drogaria.domain.ItemVenda;
import br.pro.delfino.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable{
	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;
	
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}
	
	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}
	
	@PostConstruct
	public void listar(){
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
			
			itensVenda = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os registros, erro: "+ erro);
			erro.printStackTrace();
		}		
	}
	
	public void adicionar(ActionEvent evento){
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");		
		
		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setValorParcial(produto.getValorVenda());
		itemVenda.setProduto(produto);
		itemVenda.setQtde(new Short("1"));
		
		itensVenda.add(itemVenda);
	}	
	
	public void novo(){
		
	}
	
	public void salvar(){
		
	}
	
	public void excluir(ActionEvent evento){
		
	}
	
	public void editar(ActionEvent evento){
		
	}	
}
