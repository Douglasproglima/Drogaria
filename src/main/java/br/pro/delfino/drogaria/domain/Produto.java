package br.pro.delfino.drogaria.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Produto extends GenericDomain {
	@ManyToOne
	@JoinColumn(name = "codFabricante", nullable = false, foreignKey = @ForeignKey(name = "FK_ProdutoXFabricante") )
	private Fabricante fabricante;

	@Column(length = 150, nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private Short qtde;
	
	//precision = Quantidade de digitos totais do campo;
	//scale = Quantidade de digitos depois da vírgula;
	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal valorVenda;
	
	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal valorCompra;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getQtde() {
		return qtde;
	}

	public void setQtde(Short qtde) {
		this.qtde = qtde;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}
}
