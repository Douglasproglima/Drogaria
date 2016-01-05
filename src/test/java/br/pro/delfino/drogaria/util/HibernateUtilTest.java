package br.pro.delfino.drogaria.util;

import org.hibernate.Session;
import org.junit.Test;

public class HibernateUtilTest {
	@Test
	public void conectar(){
		//Capturar a sessão aberta pela fabrica de sessões da Class HibernateUtil e armazena no objeto sessao
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		//fecha a conexão
		sessao.close();
		
		//Destroi a fabrica de sessão
		HibernateUtil.getSessionFactory().close();		
	}
}
