package br.pro.delfino.drogaria.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

// http://locahost:8080/Drogaria/[Nome_do_Rest]
@ApplicationPath("rest")
public class DrogariaResourceConfig extends ResourceConfig {
	public DrogariaResourceConfig(){
		//Caminho onde estará os serviços do webServices
		//Um serviço nada mais é do que uma class com métodos.
		packages("br.pro.delfino.drogaria.servico");
	}
	
}
