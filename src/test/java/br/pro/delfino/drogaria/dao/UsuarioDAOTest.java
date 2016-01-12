package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Pessoa;
import br.pro.delfino.drogaria.domain.Usuario;

public class UsuarioDAOTest {
	@Test
	@Ignore
	public void salvar(){
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setSenha("0103");
		usuario.setTipo('A');
		
		Long codPessoa = 2L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codPessoa);
		
		if (pessoa !=  null) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuario.setPessoa(pessoa);
			
			usuarioDAO.salvar(usuario);
			
			System.out.println("Registro salvo com sucesso!");
		} else {
			System.out.println("Registro "+codPessoa+" não encontrado no cadastro de pessoas.");
			System.out.println("Operação abordada!");
		}
	}
	
	@Test
	@Ignore
	public void listar(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> resultado = usuarioDAO.listar();
		
		System.out.println("Total registros: "+resultado.size());
		for (Usuario usuario : resultado) {
			System.out.println("Usuário: "+usuario.getCodigo()+" - "+usuario.getPessoa().getNome());
			System.out.println("Ativo..: "+usuario.getAtivo());
			System.out.println("Tipo...: "+usuario.getTipo());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codUsuario = 1L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codUsuario);
		
		System.out.println("Usuário: "+usuario.getCodigo()+" - "+usuario.getPessoa().getNome());
		System.out.println("Ativo..: "+usuario.getAtivo());
		System.out.println("Tipo...: "+usuario.getTipo());
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codUsuario = 2L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codUsuario);
		
		if (usuario != null) {
			usuarioDAO.excluir(usuario);
			System.out.println("Registro excluído com sucesso!");
		} else {
			System.out.println("Registro não encontrado!");
		}
	}
	
	@Test
	public void editar(){
		Long codUsuario = 2L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codUsuario);
		
		if (usuario != null) {
			usuario.setSenha("123456");
			usuarioDAO.editar(usuario);
			System.out.println("Registro alterado com sucesso!");
		} else {
			System.out.println("Registro não encontrado!");
		}
	}
}
