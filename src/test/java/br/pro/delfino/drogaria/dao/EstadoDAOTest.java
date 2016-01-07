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
	public void listar(){
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resutado = estadoDAO.listar();
		
		//Contador de registro no banco
		System.out.println("Total de Registro: "+resutado.size());
		
		for (Estado estado : resutado) {
			System.out.println(estado.getSigla()+" - "+estado.getNome());
		}
	}
}
