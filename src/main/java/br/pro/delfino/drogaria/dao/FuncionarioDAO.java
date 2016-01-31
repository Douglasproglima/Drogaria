package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.pro.delfino.drogaria.domain.Funcionario;
import br.pro.delfino.drogaria.util.HibernateUtil;

public class FuncionarioDAO extends GenericDAO<Funcionario>{
	@SuppressWarnings("unchecked")
	public List<Funcionario> listarOrdenado(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Funcionario.class);
			//Navegação do grafo através do relacionamento;
			//Parâmetros: Nome do atributo dentro da class, apelido (qualquer nome)
			consulta.createAlias("pessoa", "p");
			consulta.addOrder(Order.asc("p.nome"));
			
			/*Outro exemplo, neste caso poderia também passa o cidade relacionado ao pessoa;
			 * Caso precise de uma solução genérica, pesquisar pode grafos hibernate
			 *consulta.createAlias(p.cidade", "c");
			 * */
			
			List<Funcionario> resultado = consulta.list();
			
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		}finally {
			sessao.close();
		}
	}	
}
