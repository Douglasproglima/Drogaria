package com.douglasproglima.drogaria.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.douglasproglima.drogaria.dao.CaixaDAO;
import com.douglasproglima.drogaria.domain.Caixa;

public class CaixaDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException{
		Caixa caixa =  new Caixa();
		caixa.setDataAbertura(new SimpleDateFormat("dd/MM/yyyy").parse("11/02/2016"));
		caixa.setValorAbertura(new BigDecimal(100.50));
		
		CaixaDAO caixaDAO = new CaixaDAO();
		caixaDAO.merge(caixa);
	}
	
	@Test
	public void buscar() throws ParseException{
		CaixaDAO caixaDAO = new CaixaDAO();
		Caixa caixa = caixaDAO.buscar(new SimpleDateFormat("dd/MM/yyyy").parse("12/02/2016"));
		System.out.println("Caixa existe: "+caixa);
		
		//Geralmente usado em teste automatizado
		Assert.assertNull(caixa);
	}
}
