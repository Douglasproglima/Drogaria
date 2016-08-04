package com.douglasproglima.drogaria.main;

import org.hibernate.Session;

import com.douglasproglima.drogaria.util.HibernateUtil;

public class HibernateTest {
	public static void main(String[] args) {
		try {
			//Capturar a sessão aberta pela fabrica de sessões da Class HibernateUtil e armazena no objeto sessao
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			System.out.println("Conexão realizada com sucesso!");	
		
			//fecha a conexão
			sessao.close();
			//Destroi a fabrica de sessão
			HibernateUtil.getSessionFactory().close();
		} catch (Exception e) {
			System.out.println("Falha na conexão, erro: "+ e);

			//Destroi a fabrica de sessão
			HibernateUtil.getSessionFactory().close();
		}
	}
}
