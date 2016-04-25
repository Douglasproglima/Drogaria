package br.pro.delfino.drogaria.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.Hibernate;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.pro.delfino.drogaria.dao.FabricanteDAO;
import br.pro.delfino.drogaria.dao.ProdutoDAO;
import br.pro.delfino.drogaria.domain.Fabricante;
import br.pro.delfino.drogaria.domain.Produto;
import br.pro.delfino.drogaria.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable{
	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	
	@PostConstruct
	public void listar(){
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar os registros, erro: "+ erro);
			erro.printStackTrace();
		}		
	}
	
	public void novo(){
		try {
			produto = new Produto();
			
			//Instânciando o fabricante para mostrar o campo list do botão novo.
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao inserir uma nova cidade, erro: "+ erro);
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			//Validação do campo foto, condição na mão que é a mesma realizada pelo primefaces através da opção required="true"
			if (produto.getCaminho() ==  null) {
				Messages.addGlobalError("O campo Foto é obrigatório.");
				return;
			}
			
			//Trecho gerado na aula 229 upload de imagem para cópiar a imagem para um diretório padrão
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produtoRetorno = produtoDAO.mergeUpload(produto);
			
			Path origem = Paths.get(produto.getCaminho()); //Origem do caminho
			
			//O nome do arquivo após copiar para o diretório de destino será o código do produto com a extensão png
			Path destino = Paths.get("D:/AmbienteDesenvolvimento/Java/Workspace/uploadImagens/"+produtoRetorno.getCodigo()+".png"); //Destino
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			
			produto = new Produto();
			produtos = produtoDAO.listar();
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
			
			Messages.addGlobalInfo("Registro salvo com sucesso.");
		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Erro ao salvar o registro, erro: "+ erro);
			erro.printStackTrace();
		}		
	}
	
	public void excluir(ActionEvent evento){
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);
			
			//Aula 230 - Excluir imagem do caminho
			Path arquivo =  Paths.get("D:/AmbienteDesenvolvimento/Java/Workspace/uploadImagens/", produto.getCodigo()+".png");
			Files.deleteIfExists(arquivo);
			
			produtos = produtoDAO.listar();
			
			Messages.addGlobalInfo("Registro removido com sucesso.");
		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Erro ao excluir o registro, erro: "+ erro);
			erro.printStackTrace();
		}		
	}
	
	public void editar(ActionEvent evento){
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			produto.setCaminho("D:/AmbienteDesenvolvimento/Java/Workspace/uploadImagens/" + produto.getCodigo()+".png");
			
			//Instânciando o estado para mostrar o campo list do botão novo.
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao editar o registro, erro: "+ erro);
			erro.printStackTrace();
		}		
	}
	
	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload =  evento.getFile();
			Path arquivoTemp =  Files.createTempFile(null, null);

			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			produto.setCaminho(arquivoTemp.toString());
			
			Messages.addGlobalInfo("Upload realizado com sucesso");
		} catch (IOException erro) {
			Messages.addGlobalError("Erro ao realizar upload do arquivo, erro: "+ erro);
			erro.printStackTrace();
		}
	}
	
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
