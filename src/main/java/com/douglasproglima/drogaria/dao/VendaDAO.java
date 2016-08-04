package com.douglasproglima.drogaria.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.douglasproglima.drogaria.domain.ItemVenda;
import com.douglasproglima.drogaria.domain.Produto;
import com.douglasproglima.drogaria.domain.Venda;
import com.douglasproglima.drogaria.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda>{
	
	//Salva a venda e depois salva os itens da venda
	public void salvar(Venda venda, List<ItemVenda> itensVendas){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			
			//Salvar a venda
			sessao.save(venda);
			
			//Itens da venda, pois após o save já se consegue obter a chave primária gerada pelo GenaricDomain
			for (int posicao = 0; posicao < itensVendas.size(); posicao++) {
				
				//Pega o item da linha corrente, onde neste momento se tem o 
				//produto, valor parcial e qtde, faltando o código
				ItemVenda itemVenda = itensVendas.get(posicao);
				
				//Salva a venda na tabela de itens vendas
				itemVenda.setVenda(venda);
				
				//Salva o item da venda
				sessao.save(itemVenda); 
				
				//Na class Produto está a qtde em estoque e na class itemVenda está a qtde vendida
				Produto produto = itemVenda.getProduto();
				
				//Validação do saldo em estoque para não ficar negativo após a venda
				int diferenca = produto.getQtde() - itemVenda.getQtde();
				
				if (diferenca >= 0) {
					//Sempre que se trabalha com o tipo short nas operacionais matetimática o mesmo volta a ser int
					//Neste caso é necessário fazer uma conversão. Exemplo:
					//Converte para string e depois transforma em short;
					//produto.setQtde(new Short((produto.getQtde() - itemVenda.getQtde()) + ""));
					
					produto.setQtde(new Short(diferenca +""));
					
					//Atualizando o saldo em estoque
					sessao.update(produto);
				} else {
					throw new RuntimeException("Quantidade insuficiente em estoque.");
				}
			}
			
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				//Se der erro na venda ou no item da venda, cancela toda a transação realizada
				transacao.rollback();
			}
			throw erro;
		}finally {
			sessao.close();
		}
	}
}
