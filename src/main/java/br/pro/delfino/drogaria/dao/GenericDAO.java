package br.pro.delfino.drogaria.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.pro.delfino.drogaria.util.HibernateUtil;

public class GenericDAO<Entidade> {
	//API Reflection para contornar o problema da utilização do GenericDAO na pesquisa com o hibernate para saber de qual Domain
	//estamos tratando.
	public Class<Entidade> classe;
	
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void salvar(Entidade entidade){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		}finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Entidade> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			/*Existem 4 formas de trabalhar com listagem no Hibernate
			 * 1 - SQL nativo;
			 * 2 - HQL como se fosse um SQL do Hibernate;
			 * 3 - Name de Query;
			 * 4 - Criteria é a forma mais atual e que mais utiliza orientação a OO;
			 */
			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> resultado = consulta.list();
			
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		}finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Entidade buscar(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.idEq(codigo));
			
			Entidade resultado = (Entidade) consulta.uniqueResult();
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		}finally {
			sessao.close();
		}
	}	
}