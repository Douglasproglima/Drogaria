package br.pro.delfino.drogaria.bean;

import java.io.Serializable;
import java.math.BigDecimal;
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
		
		//status = -1 Não Existe o produto
		//status = +1 Existe o produto
		int status = -1;
		
		//For para percorrer o ArraList itensVenda
		for(int posicao = 0; posicao < itensVenda.size(); posicao++){
			//Pega o item na linha corrente, em seguida pega o produto do item da 
			//linha corrente onde o mesmo seja igual ao produto procurado, se atender fica +1
			if(itensVenda.get(posicao).getProduto().equals(produto)){
				status = posicao;
			}
		}
		
		if (status < 0) {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setValorParcial(produto.getValorVenda());
			itemVenda.setProduto(produto);
			itemVenda.setQtde(new Short("1"));
			
			itensVenda.add(itemVenda);	
		} else {
			ItemVenda itemVenda = itensVenda.get(status);
			itemVenda.setQtde(new Short(itemVenda.getQtde()+ 1 + ""));
			itemVenda.setValorParcial(produto.getValorVenda().multiply( new BigDecimal(itemVenda.getQtde())));
		}
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
