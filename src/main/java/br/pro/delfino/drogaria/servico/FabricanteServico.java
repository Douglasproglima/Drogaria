package br.pro.delfino.drogaria.servico;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.pro.delfino.drogaria.dao.FabricanteDAO;
import br.pro.delfino.drogaria.domain.Fabricante;

//http://127.0.0.1:8080/Drogaria/rest/fabricante
@Path("fabricante")
public class FabricanteServico {
	@GET
	public String listar(){
		/*Tipos de Projetos
		 * Json - Projeto nativo para se trabalhar com Java, atualmente a maioria dos desenvolvedores estão utilizando o Gson;
		 * Gson - Projeto da google, onde o objetivo é pegar o objeto e transformar em Json;
		 * */
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> fabricantes = fabricanteDAO.listar("descricao");
	
		Gson gson = new Gson();
		String json =  gson.toJson(fabricantes);
		
		return json;
	}
	
	//http://127.0.0.1:8080/Drogaria/rest/fabricante/codigo ou 10 filtrando
	@GET
	@Path("{codigo}")
	public String buscar(@PathParam("codigo") Long codigo){
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		
		Gson gson = new Gson();
		String json =  gson.toJson(fabricante);
		return json;
	}
}
