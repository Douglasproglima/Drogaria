package com.douglasproglima.drogaria.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Movimentacao extends GenericDomain{
	private Date horario;
	private BigDecimal valorMovimentacao;
	
	@ManyToOne
	@JoinColumn(name = "codCaixa", nullable = false, foreignKey = @ForeignKey(name = "FK_MovimentacaoXCaixa") )
	private Caixa caixa;

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getValorMovimentacao() {
		return valorMovimentacao;
	}

	public void setValorMovimentacao(BigDecimal valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}
}
