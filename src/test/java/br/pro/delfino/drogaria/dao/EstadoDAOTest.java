package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Estado;

public class EstadoDAOTest {
	//Anotação @Ignore faz com que o método salvar não seja executado pelo JUnit
	@Test
	@Ignore
	public void salvar(){
		Estado estado = new Estado();
		estado.setNome("Minas Gerais");
		estado.setSigla("MG");

		Estado estado1 = new Estado();
		estado1.setNome("São Paulo");
		estado1.setSigla("SP");
		
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
		estadoDAO.salvar(estado1);
	}
	
	@Test
	@Ignore
	public void listar(){
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resutado = estadoDAO.listar();
		
		//Contador de registro no banco
		System.out.println("Total de Registro: "+resutado.size());
		
		for (Estado estado : resutado) {
			System.out.println(estado.getSigla()+" - "+estado.getNome());
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codigo = 5L;
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if (estado != null) {
			System.out.println(estado.getSigla()+" - "+estado.getNome());
		} else {
			System.out.println("Registro não encontrado.");
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 3L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if (estado != null) {
			estadoDAO.excluir(estado);
			System.out.println("Registro excluído com sucesso.");
		} else {
			System.out.println("Registro não encontrado para ser excluído.");
		}
	}
	
	@Test
	public void editar(){
		Long codigo = 4L;
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if (estado != null) {
			System.out.println("Registro alterado anterior: "+estado.getSigla()+" - "+estado.getNome());
			
			estado.setSigla("ES");
			estado.setNome("ESPIRITO SANTO");
			estadoDAO.editar(estado);
			
			System.out.println("Registro atual: "+estado.getSigla()+" - "+estado.getNome());
		} else {
			System.out.println("Registro não encontrado.");
		}
	}
}
