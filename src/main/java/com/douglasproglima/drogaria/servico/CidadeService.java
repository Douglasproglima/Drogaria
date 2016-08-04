package com.douglasproglima.drogaria.servico;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.douglasproglima.drogaria.dao.CidadeDAO;
import com.douglasproglima.drogaria.domain.Cidade;
import com.google.gson.Gson;

//http://127.0.0.1:8080/Drogaria/rest/cidade
@Path("cidade")
public class CidadeService {
	@GET
	@Path("{estadoCodigo}")
	public String buscarPorEstado(@PathParam("estadoCodigo") Long estadoCodigo) {
		/*
		 * Tipos de Projetos Json - Projeto nativo para se trabalhar com Java,
		 * atualmente a maioria dos desenvolvedores estão utilizando o Gson;
		 * Gson - Projeto da google, onde o objetivo é pegar o objeto e
		 * transformar em Json;
		 */

		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.buscarPorEstado(estadoCodigo);
		
		Gson gson = new Gson();
		String json = gson.toJson(cidades);

		return json;
	}
}