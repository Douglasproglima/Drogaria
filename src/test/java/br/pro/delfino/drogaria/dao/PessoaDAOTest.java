package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Cidade;
import br.pro.delfino.drogaria.domain.Pessoa;

public class PessoaDAOTest {
	@Test
	@Ignore
	public void salvar(){
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Augusto");
		pessoa.setCpf("07565498796");
		pessoa.setRg("MG-15653265");
		pessoa.setRua("Rua das Flores");
		pessoa.setNumero((short) 101);
		pessoa.setBairro("São Gerado");
		pessoa.setCep("35680-00");
		pessoa.setComplemento("Casa");
		pessoa.setTelefone("37 9865 3221");
		pessoa.setCelular("37 3241 6598");
		pessoa.setEmail("augusto@hotmail.com");
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(1L);
		
		
		if (cidade != null) {
			pessoa.setCidade(cidade);
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.salvar(pessoa);
			System.out.println("Registro salvo com sucesso!");
		} else {
			System.out.println("Cidade não encontrada!");
			System.out.println("Operação abortada!");
		}
	}
	
	@Test
	@Ignore
	public void listar(){
		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> resultado = pessoaDAO.listar();
		
		System.out.println("Total registros: "+resultado.size());
		for (Pessoa pessoa : resultado) {
			System.out.println("Estado: "+pessoa.getCidade().getEstado().getSigla());
			System.out.println("Cidade: "+pessoa.getCidade().getCodigo()+" - "+pessoa.getCidade().getNome());
			System.out.println("Bairro: "+pessoa.getBairro());
			System.out.println("Pessoa: "+pessoa.getCodigo()+" - "+pessoa.getNome());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codPessoa = 1L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codPessoa);
		
		if (pessoa != null) {
			System.out.println("Estado: "+pessoa.getCidade().getEstado().getSigla());
			System.out.println("Cidade: "+pessoa.getCidade().getCodigo()+" - "+pessoa.getCidade().getNome());
			System.out.println("Bairro: "+pessoa.getBairro());
			System.out.println("Pessoa: "+pessoa.getCodigo()+" - "+pessoa.getNome());
		} else {
			System.out.println("Registro não encontrado!");
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codPessoa = 5L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codPessoa);
		
		if (pessoa != null) {
			pessoaDAO.excluir(pessoa);
			System.out.println("Registro excluído com sucesso!");
		} else {
			System.out.println("Registro não encontrado!");
		}
	}
	
	@Test
	@Ignore
	public void editar(){
		Long codPessoa = 4L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codPessoa);
		
		if (pessoa != null) {
			pessoa.setNome("Pessoa alterada");
			CidadeDAO cidadeDAO = new CidadeDAO();
			Cidade cidade = cidadeDAO.buscar(2L);
			
			if (cidade != null) {
				pessoa.setCidade(cidade);
			}
			pessoaDAO.editar(pessoa);
			System.out.println("Registro salvo com sucesso!");
		} else {
			System.out.println("Registro não encontrado!");
		}
	}
}
