package com.douglasproglima.drogaria.servico;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

//http://locahost:8080/Drogaria/rest/[Nome do Repositorio de serviço] = drogaria
@Path("drogaria")
public class DrogariaService {
	/*Tipos de chamadas de um serviço:
	 * GET.: Se usa quando se quer consulta alguma coisa;
	 * POST: 
	 * PUT.: 
	 * */
	
	@GET
	public String exibir(){
		return "Testando WebServices Java EE";
	}
}
