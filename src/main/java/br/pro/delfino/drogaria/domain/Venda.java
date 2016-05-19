package br.pro.delfino.drogaria.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Venda extends GenericDomain{
	@ManyToOne
	@JoinColumn(name = "codCliente", foreignKey = @ForeignKey(name = "FK_VendaXCliente"))
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "codFuncionario", nullable = false, foreignKey = @ForeignKey(name = "FK_VendaXFuncionario"))
	private Funcionario funcionario;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;
	
	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal valorTotal;
	
	//Uma venda poderá ter vários itens da venda FetchType.EAGER = Irá carregar os itens no mesmo momento que carregar a venda
	//Foi necessário pois fecho a sessão a todo momento. E para este esquema é necessário ter a sessão aberta.
	//mappedBy = Nome do objeto pai que está na class Vendas, é o como se fosse a PK que relaciona com a FK
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "venda")
	private List<ItemVenda> itensVendas;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public List<ItemVenda> getItensVendas() {
		return itensVendas;
	}
	
	public void setItensVendas(List<ItemVenda> itensVendas) {
		this.itensVendas = itensVendas;
	}
}