package com.douglasproglima.drogaria.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.douglasproglima.drogaria.domain.Usuario;
import com.douglasproglima.drogaria.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario>{
	
	//Criptografia da senha através do SimpleHash
	public Usuario autenticar(String nome, String senha){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p");
			
			consulta.add(Restrictions.eq("p.nome", nome));
//			consulta.add(Restrictions.eq("p.cpf", nome));
			
			//Pega a senha normal e passa para o simpleHash criptografar com  md5
			SimpleHash hash =  new SimpleHash("md5", senha);
			//passa a senha criptografada
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		}finally {
			sessao.close();
		}
	}

	public Usuario autenticar2(String user, String senha){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("usuario", "u");
			
			consulta.add(Restrictions.eq("u.usuario", user));
//			consulta.add(Restrictions.eq("p.cpf", nome));
			
			//Pega a senha normal e passa para o simpleHash criptografar com  md5
			SimpleHash hash =  new SimpleHash("md5", senha);
			//passa a senha criptografada
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		}finally {
			sessao.close();
		}
	}	
	
}
