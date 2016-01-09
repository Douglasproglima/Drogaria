package br.pro.delfino.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Funcionario;
import br.pro.delfino.drogaria.domain.Pessoa;

public class FuncionarioDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException{
		Funcionario funcionario = new Funcionario();
		funcionario.setAtivo(true);
		funcionario.setCarteiraTrabalho("123456789");
		funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1987"));
		
		Long codPessoa = 4L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codPessoa);
		if (pessoa != null) {
			funcionario.setPessoa(pessoa);
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.salvar(funcionario);
			
			System.out.println("Registro salvo com sucesso!");
		} else {
			System.out.println("Registro "+codPessoa+" não encontrado no cadastro de pessoas.");
			System.out.println("Operação abordada!");
		}
	}
	
	@Test
	@Ignore
	public void listar(){
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> resultado = funcionarioDAO.listar();
		
		System.out.println("Total registros: "+resultado.size());
		for (Funcionario funcionario : resultado) {
			System.out.println("Funcionário: "+funcionario.getCodigo()+" - "+funcionario.getPessoa().getNome());
			System.out.println("Ativo......: "+funcionario.getAtivo());
			System.out.println("Data Admissão: "+funcionario.getDataAdmissao());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codFuncionario = 1L;
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codFuncionario);
		
		if (funcionario != null) {
			System.out.println("Funcionário: "+funcionario.getCodigo()+" - "+funcionario.getPessoa().getNome());
			System.out.println("Ativo......: "+funcionario.getAtivo());
			System.out.println("Data Admissão: "+funcionario.getDataAdmissao());			
		} else {
			System.out.println("Registro não encontrado!");
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codFuncionario = 4L;
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codFuncionario);
		
		if (funcionario != null) {
			funcionarioDAO.excluir(funcionario);
			System.out.println("Registro excluído com sucesso!");
		} else {
			System.out.println("Registro não encontrado!");
		}
	}
	
	@Test
	@Ignore
	public void editar() throws ParseException{
		Long codFuncionario = 3L;
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codFuncionario);
		
		if (funcionario != null) {
			Long codPessoa = 4L;
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.buscar(codPessoa);
			if (pessoa != null) {
				funcionario.setPessoa(pessoa);
			}
			
			funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1998"));
			funcionarioDAO.editar(funcionario);
			System.out.println("Registro salvo com sucesso!");
		} else {
			System.out.println("Registro não encontrado!");
		}
	}
}
