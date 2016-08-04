package com.douglasproglima.drogaria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.douglasproglima.drogaria.domain.Cliente;
import com.douglasproglima.drogaria.util.HibernateUtil;

public class ClienteDAO extends GenericDAO<Cliente>{
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listarOrdenado(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Cliente.class);
			//Navegação do grafo através do relacionamento;
			//Parâmetros: Nome do atributo dentro da class, apelido (qualquer nome)
			consulta.createAlias("pessoa", "p");
			consulta.addOrder(Order.asc("p.nome"));
			List<Cliente> resultado = consulta.list();
			
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		}finally {
			sessao.close();
		}
	}
}
