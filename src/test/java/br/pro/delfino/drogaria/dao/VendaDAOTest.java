package br.pro.delfino.drogaria.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Cliente;
import br.pro.delfino.drogaria.domain.Funcionario;
import br.pro.delfino.drogaria.domain.Venda;

public class VendaDAOTest {
	@Test
	@Ignore
	public void salvar(){
		Venda venda = new Venda();
		venda.setHorario(new Date());
		venda.setValorTotal(new BigDecimal(1));
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(2L);
		if (cliente !=  null) {
			venda.setCliente(cliente);
		}
		
		Long codFuncionario = 1L;
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codFuncionario);
		if (funcionario != null) {
			VendaDAO vendaDAO = new VendaDAO();
			venda.setFuncionario(funcionario);
			
			vendaDAO.salvar(venda);
			System.out.println("Registro salvo com sucesso!");
		} else {
			System.out.println("Registro "+codFuncionario+" não encontrado no cadastro de funcionários.");
			System.out.println("Operação abordada!");
		}
	}
	
	@Test
	@Ignore
	public void listar(){
		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> resultado = vendaDAO.listar();
		
		System.out.println("Total registros: "+resultado.size());
		for (Venda venda : resultado) {
			System.out.println("Funcionário: "+venda.getFuncionario().getCodigo()+" - "+venda.getFuncionario().getPessoa().getNome());
			System.out.println("Cliente....: "+venda.getCliente().getCodigo()+" - "+venda.getCliente().getPessoa().getNome());
			System.out.println("Hora Venda.: "+venda.getHorario());
			System.out.println("Valor Total: R$"+venda.getValorTotal());
			System.out.println();
		}
	}
	
	@Test
	public void buscar(){
		Long codVenda = 1L;
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codVenda);
		
		if (venda != null) {
			System.out.println("Funcionário: "+venda.getFuncionario().getCodigo()+" - "+venda.getFuncionario().getPessoa().getNome());
			System.out.println("Cliente....: "+venda.getCliente().getCodigo()+" - "+venda.getCliente().getPessoa().getNome());
			System.out.println("Hora Venda.: "+venda.getHorario());
			System.out.println("Valor Total: R$"+venda.getValorTotal());			
		} else {
			System.out.println("Registro não encontrado!");
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codVenda = 5L;
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codVenda);
		
		if (venda != null) {
			vendaDAO.excluir(venda);
			System.out.println("Registro excluído com sucesso!");
		} else {
			System.out.println("Registro não encontrado!");
		}
	}
	
	@Test
	public void editar(){
		Long codVenda = 4L;
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codVenda);
		
		if (venda != null) {
			venda.setValorTotal(new BigDecimal(193));
			vendaDAO.editar(venda);
			System.out.println("Registro salvo com sucesso!");
		} else {
			System.out.println("Registro não encontrado!");
		}
	}
}
