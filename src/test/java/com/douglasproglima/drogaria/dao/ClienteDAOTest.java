package com.douglasproglima.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.douglasproglima.drogaria.dao.ClienteDAO;
import com.douglasproglima.drogaria.dao.PessoaDAO;
import com.douglasproglima.drogaria.domain.Cliente;
import com.douglasproglima.drogaria.domain.Pessoa;

public class ClienteDAOTest {
	@Test
	@Ignore
	public void salvar(){
		Cliente cliente = new Cliente();
		cliente.setAtivo(true);
		cliente.setDataCadastro(new Date());
		
		Long codPessoa = 4L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codPessoa);
		
		if (pessoa != null) {
			cliente.setPessoa(pessoa);
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.salvar(cliente);
			
			System.out.println("Registro salvo com sucesso!");
		} else {
			System.out.println("Registro "+codPessoa+" não encontrado no cadastro de pessoas.");
			System.out.println("Operação abordada!");
		}
	}
	
	@Test
	@Ignore
	public void listart(){
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> resultar = clienteDAO.listar();
		
		System.out.println("Total registros: "+resultar.size());
		for (Cliente cliente : resultar) {
			System.out.println("Código.: "+cliente.getCodigo());
			System.out.println("Cliente: "+cliente.getPessoa().getCodigo()+" - "+cliente.getPessoa().getNome());
			System.out.println("Data Cadastro: "+cliente.getDataCadastro());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(5L);
		
		if (cliente != null) {
			System.out.println("Código.: "+cliente.getCodigo());
			System.out.println("Cliente: "+cliente.getPessoa().getCodigo()+" - "+cliente.getPessoa().getNome());
			System.out.println("Data Cadastro: "+cliente.getDataCadastro());
		} else {
			System.out.println("Registro não encontrado!");
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(5L);
		
		if (cliente != null) {
			clienteDAO.excluir(cliente);
			System.out.println("Registro excluído com sucesso!");
		} else {
			System.out.println("Registro não encontrado, operação abortada!");
		}
	}
	
	@Test
	public void editar() throws ParseException{
		Long codCliente = 5L;
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codCliente);
		
		if (cliente != null) {
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.buscar(5L);
			if (pessoa != null) {
				cliente.setPessoa(pessoa);
			}
			
			cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("04/01/2015"));
			clienteDAO.editar(cliente);
			System.out.println("Registro salvo com sucesso!");
		} else {
			System.out.println("Registro não encontrado!");
		}
	}
}
