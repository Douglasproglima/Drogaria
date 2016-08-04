package com.douglasproglima.drogaria.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.map.HashedMap;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

import com.douglasproglima.drogaria.dao.FabricanteDAO;
import com.douglasproglima.drogaria.dao.ProdutoDAO;
import com.douglasproglima.drogaria.domain.Fabricante;
import com.douglasproglima.drogaria.domain.Produto;
import com.douglasproglima.drogaria.util.HibernateUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoListagemBean implements Serializable{
	private Produto produto;
	private Long codigoProduto;
	
	private List<Fabricante> fabricantes;
	private List<Produto> produtos;
	
	private ProdutoDAO produtoDAO;
	private FabricanteDAO fabricanteDAO;
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	
	public Long getCodigoProduto() {
		return codigoProduto;
	}
	
	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	
	//O @PostConstruct resumindo ele simplesmente dá new;
	@PostConstruct
	public void iniciar(){
		produtoDAO = new ProdutoDAO();
		fabricanteDAO = new FabricanteDAO();
			
	}
	
	public void listar(){
		try {
			produtos = produtoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os registros, erro: "+ erro);
			erro.printStackTrace();
		}		
	}
	
	public void carregarEdicao(){
		try {
			produto = produtoDAO.buscar(codigoProduto);
			fabricantes = fabricanteDAO.listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os registros para edição, erro: "+ erro);
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.merge(produto);
			
			produtos = produtoDAO.listar();
			fabricantes = fabricanteDAO.listar();
			
			Messages.addGlobalInfo("Registro salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o registro, erro: "+ erro);
			erro.printStackTrace();
		}		
	}

	public void excluir(ActionEvent evento){
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			produtoDAO.excluir(produto);
			
			produtos = produtoDAO.listar();
			
			Messages.addGlobalInfo("Registro removido com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao excluir o registro, erro: "+ erro);
			erro.printStackTrace();
		}		
	}	
	
	@SuppressWarnings("unchecked")
	public void imprimir(){
		try {
			//Arvore de componente da aplicação
			//Faces.getViewRoot().findComponent("idFormulario:idComponente");
			DataTable tabela =  (DataTable) Faces.getViewRoot().findComponent("formListagem:tabelaListagem");
			Map<String, Object> filtros = tabela.getFilters();
			
			//Parâmetros
			String prodDescricao = (String) filtros.get("descricao");
			String fabDescricao = (String) filtros.get("fabricante.descricao");
			
			String caminho = Faces.getRealPath("/relatorios/produtosListagem.jasper");
			
			Messages.addGlobalInfo("Caminho: " + caminho);
			
			Map<String, Object> parametro = new HashedMap(); //Nome e valor do parâmetro
			
			//Passagem de parâmetros para o relatório
			//1 - Nome do parametro do relatório na query;
			//2 - Nome do parametro da aplicação
			if (prodDescricao == null) {
				parametro.put("PRODUTO_DESCRICAO", "%%");
			} else {
				parametro.put("PRODUTO_DESCRICAO", "%"+prodDescricao+"%");
			}
			
			if (fabDescricao == null) {
				parametro.put("FABRICANTE_DESCRICAO", "%%");
			} else {
				parametro.put("FABRICANTE_DESCRICAO", "%"+fabDescricao+"%");
			}
			
			Connection conexao = HibernateUtil.getConexao();
			
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametro, conexao);
			
			JasperPrintManager.printReport(relatorio, true);
		} catch (JRException erro) {
			Messages.addGlobalError("Erro ao imprimir relatório, erro: "+ erro);
			erro.printStackTrace();
		}
	}
}