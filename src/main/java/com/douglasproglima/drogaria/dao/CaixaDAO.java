package com.douglasproglima.drogaria.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.douglasproglima.drogaria.domain.Caixa;
import com.douglasproglima.drogaria.util.HibernateUtil;

public class CaixaDAO extends GenericDAO<Caixa>{
	public Caixa buscar(Date dataAbertura){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Caixa.class);
			consulta.add(Restrictions.eq("dataAbertura", dataAbertura));
			
			Caixa resultado = (Caixa) consulta.uniqueResult();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		}finally {
			sessao.close();
		}
	}
}
