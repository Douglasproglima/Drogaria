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
import br.pro.delfino.drogaria.domain.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {
	private Venda venda;
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
	
	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	@PostConstruct
	public void novo() {
		try {
			venda =  new Venda();
			venda.setValorTotal(new BigDecimal("0.00"));
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensVenda = new ArrayList<>();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os registros, erro: " + erro);
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		// status = -1 Não Existe o produto
		// status = +1 Existe o produto
		int status = -1;

		// For para percorrer o ArraList itensVenda
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			// Pega o item na linha corrente, em seguida pega o produto do item
			// da
			// linha corrente onde o mesmo seja igual ao produto procurado, se
			// atender fica +1
			if (itensVenda.get(posicao).getProduto().equals(produto)) {
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
			itemVenda.setQtde(new Short(itemVenda.getQtde() + 1 + ""));
			itemVenda.setValorParcial(produto.getValorVenda().multiply(new BigDecimal(itemVenda.getQtde())));
		}
		
		//Após adicionar os itens do carrinho atualiza o valor total através do método calcular()
		calcular();
	}

	public void remover(ActionEvent evento) {
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemVendaSelecionado");

		// status = -1 Não Existe o produto
		// status = +1 Existe o produto
		int status = -1;
		for(int posicao = 0; posicao < itensVenda.size(); posicao++){
			//Se for o mesmo produto excluir, captura a linha corrente(posição), em seguida captura o produto dele.
			//Em seguida verifica se o item que veio do arrayList é igual ao delete;
			//Compara se o produto que veio do botão é igual ao produto que veio da tabela;
			if (itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())) {
				status = posicao;
			}
		}
		
		//Se for maior que -1 pode remover
		if (status > -1) {
			itensVenda.remove(status);
		}
		
		//Após adicionar os itens do carrinho atualiza o valor total através do método calcular()
		calcular();
	}

	public void calcular(){
		//Zera o totalizar
		venda.setValorTotal(new BigDecimal("0.00"));
		
		//For do ArrayList itemVendas;
		for(int posicao = 0; posicao < itensVenda.size(); posicao++){
			//Captura o item da venda corrente, ou seja a cada repetição do carrinho de compra um item de cada vez
			ItemVenda itemVenda =  itensVenda.get(posicao);
			//Pega o valor total mais o valor parcial e seta no valor total;
			venda.setValorTotal(venda.getValorTotal().add(itemVenda.getValorParcial()));
		}
	}
	
	public void salvar() {

	}

	public void excluir(ActionEvent evento) {

	}

	public void editar(ActionEvent evento) {

	}
}
