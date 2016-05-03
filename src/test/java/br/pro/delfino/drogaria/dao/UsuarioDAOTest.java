package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Pessoa;
import br.pro.delfino.drogaria.domain.Usuario;

public class UsuarioDAOTest {
	@Test
//	@Ignore
	public void salvar(){
		Usuario usuario = new Usuario();
		
		Long codPessoa = 1L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codPessoa);
		
		if (pessoa !=  null) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuario.setPessoa(pessoa);
			
			usuario.setAtivo(true);
			usuario.setTipo('A');
			
			usuario.setSenhaSemCriptografia("123456");
			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
			usuario.setSenha(hash.toHex());
			
			usuarioDAO.merge(usuario);
			
			System.out.println("Registro salvo com sucesso!");
		} else {
			System.out.println("Registro "+codPessoa+" não encontrado no cadastro de pessoas.");
			System.out.println("Operação abordada!");
		}
	}
	
	@Test
	public void autenticar(){
		String nome = "douglas.fernando";
		String cpf = "076.392.316-80";
		String senha = "123456";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(nome, senha);
		
		//Se retornar <> de null funcionou
		System.out.println("Usuário autenticado: " + usuario);
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
	@Ignore
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
