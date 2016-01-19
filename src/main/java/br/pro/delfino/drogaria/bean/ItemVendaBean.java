package br.pro.delfino.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.pro.delfino.drogaria.domain.ItemVenda;
import br.pro.delfino.drogaria.domain.Produto;
import br.pro.delfino.drogaria.domain.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ItemVendaBean implements Serializable{
	private ItemVenda itemVenda;
	private List<ItemVenda> itemVendas;
	
	private List<Produto> produtos;
	private List<Venda> vendas;
	public ItemVenda getItemVenda() {
		return itemVenda;
	}
	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}
	public List<ItemVenda> getItemVendas() {
		return itemVendas;
	}
	public void setItemVendas(List<ItemVenda> itemVendas) {
		this.itemVendas = itemVendas;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public List<Venda> getVendas() {
		return vendas;
	}
	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	@PostConstruct
	public void listar(){
		
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
