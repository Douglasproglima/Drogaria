package com.douglasproglima.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.douglasproglima.drogaria.dao.FabricanteDAO;
import com.douglasproglima.drogaria.dao.ProdutoDAO;
import com.douglasproglima.drogaria.domain.Fabricante;
import com.douglasproglima.drogaria.domain.Produto;

public class ProdutoDAOTest {
	@Test
	@Ignore
	public void salvar(){
		Produto produto = new Produto();
		
		produto.setDescricao("PRODUTO DELETE");
		produto.setQtde((short) 10);
		produto.setValorCompra(new BigDecimal(1.90));
		produto.setValorVenda(new BigDecimal(2.90));
		Long codigo = 6L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		produto.setFabricante(fabricante);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
	}
	
	@Test
	@Ignore
	public void listar(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resutaldo = produtoDAO.listar();
		
		System.out.println("Total registros: "+resutaldo.size());
		for (Produto produto : resutaldo) {
			System.out.println("Fabricante: "+produto.getFabricante().getCodigo()+" - "+produto.getFabricante().getDescricao());
			System.out.println("Produto...: "+produto.getCodigo()+" - "+produto.getDescricao());
			System.out.println("Qtde......: "+produto.getQtde());
			System.out.println("Vlr Compra: R$"+produto.getValorCompra());
			System.out.println("Vlr Venda.: R$"+produto.getValorVenda());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codProduto = 4L;
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codProduto);
		
		if (produto != null) {
			System.out.println("Fabricante: "+produto.getFabricante().getCodigo()+" - "+produto.getFabricante().getDescricao());
			System.out.println("Produto...: "+produto.getCodigo()+" - "+produto.getDescricao());
			System.out.println("Qtde......: "+produto.getQtde());
			System.out.println("Vlr Compra: R$"+produto.getValorCompra());
			System.out.println("Vlr Venda.: R$"+produto.getValorVenda());
		} else {
			System.out.println("Nenhum registro encontrado.");
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codProduto = 8L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codProduto);
		
		if (produto != null) {
			produtoDAO.excluir(produto);
			System.out.println("Registro excluído com sucesso.");
		} else {
			System.out.println("Registro não encontrado para ser excluído.");
		}
	}
	
	@Test
	public void editar(){
		Long codProduto = 7L;
		ProdutoDAO produtoDAO =  new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codProduto);
		
		if (produto != null) {
			Long codFabricante = 1L;
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			Fabricante fabricante = fabricanteDAO.buscar(codFabricante);
			
			if (fabricante !=  null) {
				produto.setFabricante(fabricante);
			}
			produto.setDescricao("MINOXIDIL");
			
			produtoDAO.editar(produto);
			System.out.println("Registrado alterado com sucesso!");
		} else {
			System.out.println("Registrado não encontrado para alteração.");
		}
	}
}
