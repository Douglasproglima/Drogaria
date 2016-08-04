package com.douglasproglima.drogaria.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Historico extends GenericDomain {
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHorario;
	
	@Column(nullable=false, length = 1024)
	private String observacoes;
	
	@ManyToOne
	@JoinColumn(name = "codProduto", nullable = false, foreignKey = @ForeignKey(name = "FK_ProdutoXHistorico") )
	private Produto produto;

	public Date getDataHorario() {
		return dataHorario;
	}

	public void setDataHorario(Date dataHorario) {
		this.dataHorario = dataHorario;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
