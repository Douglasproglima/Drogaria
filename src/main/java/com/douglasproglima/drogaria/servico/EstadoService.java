package com.douglasproglima.drogaria.servico;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.douglasproglima.drogaria.dao.EstadoDAO;
import com.douglasproglima.drogaria.domain.Estado;
import com.google.gson.Gson;

//http://127.0.0.1:8080/Drogaria/rest/estado
@Path("estado")
public class EstadoService {
	@GET
	public String listar() {
		/*
		 * Tipos de Projetos Json - Projeto nativo para se trabalhar com Java,
		 * atualmente a maioria dos desenvolvedores estão utilizando o Gson;
		 * Gson - Projeto da google, onde o objetivo é pegar o objeto e
		 * transformar em Json;
		 */

		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> estados = estadoDAO.listar("nome");

		Gson gson = new Gson();
		String json = gson.toJson(estados);

		return json;
	}
}