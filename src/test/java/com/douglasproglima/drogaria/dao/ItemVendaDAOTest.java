package com.douglasproglima.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.douglasproglima.drogaria.dao.ItemVendaDAO;
import com.douglasproglima.drogaria.dao.ProdutoDAO;
import com.douglasproglima.drogaria.dao.VendaDAO;
import com.douglasproglima.drogaria.domain.ItemVenda;
import com.douglasproglima.drogaria.domain.Produto;
import com.douglasproglima.drogaria.domain.Venda;

public class ItemVendaDAOTest {
	@Test
	@Ignore
	public void salvar(){
		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setQtde((short) 2); 
		itemVenda.setValorParcial(new BigDecimal(25.56));
	
		Long codVenda = 4L;
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codVenda);
		
		Long codProduto = 5L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codProduto);
		
		if (venda != null && produto != null ) {
			ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
			itemVenda.setVenda(venda);
			itemVenda.setProduto(produto);
			itemVendaDAO.salvar(itemVenda);
			
			System.out.println("Registro salvo com sucesso!");
		} else {
			System.out.println("Código da Venda: "+codVenda+" ou código do produto: "+codProduto+", não encontrado no sistema.");
			System.out.println("Operação abordada!");
		}
	}
	
	@Test
	@Ignore
	public void listar(){
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		List<ItemVenda> resultado = itemVendaDAO.listar();
		
		System.out.println("Total registros: "+resultado.size());
		for (ItemVenda itemVenda : resultado) {
			System.out.println("Código da Venda: "+itemVenda.getVenda().getCodigo());
			System.out.println("Produto........: "+itemVenda.getProduto().getCodigo()+" - "+itemVenda.getProduto().getDescricao());
			System.out.println("Qtde...........: "+itemVenda.getQtde());
			System.out.println("Valor Unitário.: "+itemVenda.getValorParcial());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(1L);
		
		System.out.println("Código da Venda: "+itemVenda.getVenda().getCodigo());
		System.out.println("Produto........: "+itemVenda.getProduto().getCodigo()+" - "+itemVenda.getProduto().getDescricao());
		System.out.println("Qtde...........: "+itemVenda.getQtde());
		System.out.println("Valor Unitário.: "+itemVenda.getValorParcial());
	}
	
	@Test
	@Ignore
	public void excluir(){
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(2L);
		
		itemVendaDAO.excluir(itemVenda);
		System.out.println("Registro excluído com sucesso!");
	}
	
	@Test
	public void editar(){
		Long codVenda = 5L;
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		ItemVenda itemVenda = itemVendaDAO.buscar(codVenda);
		
		itemVenda.setQtde((short) 4);
		itemVenda.setValorParcial(new BigDecimal(6.55));
		
		itemVendaDAO.editar(itemVenda);
		System.out.println("Registro alterado!");
	}
}
