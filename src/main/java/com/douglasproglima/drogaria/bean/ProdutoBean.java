package com.douglasproglima.drogaria.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.map.HashedMap;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

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
public class ProdutoBean implements Serializable{
	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;
	
	private StreamedContent fotoDown;
	
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
	
	public StreamedContent getFotoDown() {
		return fotoDown;
	}
	
	public void setFotoDown(StreamedContent fotoDown) {
		this.fotoDown = fotoDown;
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
			String caminhoLogo = Faces.getRealPath("/resources/imagens/Logo.jpg");
			
			//Messages.addGlobalInfo("Caminho: " + caminhoLogo);
			
			Map<String, Object> parametro = new HashedMap(); //Nome e valor do parâmetro
			
			//Passa o caminho da logo para o relatório de produto, componente de imagem do relatório.
			parametro.put("CaminhoLogo", caminhoLogo);
			
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
	
	//Método Donwload utilizando o componente <p:fileDownload /> Aula 298
	public void donwload(ActionEvent evento){
		try{
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			InputStream stream = new FileInputStream("D:/AmbienteDesenvolvimento/Java/Workspace/uploadImagens/"+produto.getCodigo()+".png");
			fotoDown = new DefaultStreamedContent(stream, "image/png", produto.getCodigo() + ".png");
		
		} catch (FileNotFoundException erro) {
			Messages.addGlobalError("Erro ao tentar efetuar o donwload da imagem, erro: "+ erro);
			erro.printStackTrace();
		}
	}
}
