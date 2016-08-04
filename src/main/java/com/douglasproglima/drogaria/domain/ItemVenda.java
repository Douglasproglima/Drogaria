package com.douglasproglima.drogaria.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class ItemVenda extends GenericDomain{
	@ManyToOne
	@JoinColumn(name = "codVenda", nullable = false, foreignKey = @ForeignKey(name = "FK_ItemVendaXVenda"))
	private Venda venda;
	
	@ManyToOne
	@JoinColumn(name = "codProduto", nullable = false, foreignKey = @ForeignKey(name = "FK_ItemVendaXProduto"))
	private Produto produto;
	
	@Column(nullable = false)
	private Short qtde;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal valorParcial;

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Short getQtde() {
		return qtde;
	}

	public void setQtde(Short qtde) {
		this.qtde = qtde;
	}

	public BigDecimal getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}
}
