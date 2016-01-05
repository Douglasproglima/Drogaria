package br.pro.delfino.main;

import org.hibernate.Session;

import br.pro.delfino.drogaria.util.HibernateUtil;

public class HibernateTest {
	public static void main(String[] args) {
		//Capturar a sessão aberta pela fabrica de sessões da Class HibernateUtil e armazena no objeto sessao
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		//fecha a conexão
		sessao.close();
		
		//Destroi a fabrica de sessão
		HibernateUtil.getSessionFactory().close();
	}
}
