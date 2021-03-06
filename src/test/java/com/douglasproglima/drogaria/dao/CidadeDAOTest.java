package com.douglasproglima.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.douglasproglima.drogaria.dao.CidadeDAO;
import com.douglasproglima.drogaria.dao.EstadoDAO;
import com.douglasproglima.drogaria.domain.Cidade;
import com.douglasproglima.drogaria.domain.Estado;

public class CidadeDAOTest {
	@Test
	@Ignore
	public void salvar(){
		Long codEstado = 2L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codEstado);
		
		Cidade cidade = new Cidade();
		cidade.setNome("Belo Horizonte");
		cidade.setEstado(estado);
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}
	
	@Test
	@Ignore
	public void listar(){
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();
		
		System.out.println("Total de Registro: "+resultado.size());
		for (Cidade cidade : resultado) {
			System.out.println("Cidade: "+cidade.getCodigo()+" - "+cidade.getNome());
			System.out.println("Estado: "+cidade.getEstado().getSigla()+" - "+cidade.getEstado().getNome());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codigo = 1L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		if (cidade != null) {
			System.out.println("Cidade: "+cidade.getCodigo()+" - "+cidade.getNome());
			System.out.println("Estado: "+cidade.getEstado().getSigla()+" - "+cidade.getEstado().getNome());
		} else {
			System.out.println("Nenhum registro encontrado.");
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 9L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		if (cidade != null) {
			cidadeDAO.excluir(cidade);
			System.out.println("Registro excluído com sucesso.");
		} else {
			System.out.println("Registro não encontrado para ser excluído.");
		}
	}
	
	@Test
	@Ignore
	public void editar(){
		Long codigoCidade = 7L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		if (cidade != null) {
			Long codigoEstado = 1L;
			
			EstadoDAO estadoDAO = new EstadoDAO();
			Estado estado = estadoDAO.buscar(codigoEstado);
			
			cidade.setEstado(estado);
			//cidade.setNome("Vila Velha");
			cidadeDAO.editar(cidade);
			
			System.out.println("Registro atualizado com sucesso!");
		} else {
			System.out.println("Registro não encontrado para alteração");
		}
	}
	
	@Test
	public void buscarPorEstado(){
		Long estadoCodigo = 1L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.buscarPorEstado(estadoCodigo);
		
		System.out.println("Total de Registro: "+resultado.size());
		for (Cidade cidade : resultado) {
			System.out.println("Cidade: "+cidade.getCodigo()+" - "+cidade.getNome());
			System.out.println("Estado: "+cidade.getEstado().getSigla()+" - "+cidade.getEstado().getNome());
			System.out.println();
		}
	}	
}
