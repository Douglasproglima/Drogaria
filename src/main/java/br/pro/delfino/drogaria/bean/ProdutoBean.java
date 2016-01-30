package br.pro.delfino.drogaria.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.pro.delfino.drogaria.dao.FabricanteDAO;
import br.pro.delfino.drogaria.dao.ProdutoDAO;
import br.pro.delfino.drogaria.domain.Fabricante;
import br.pro.delfino.drogaria.domain.Produto;

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
			ProdutoDAO produtoDAO = new ProdutoDAO();
			
			produtoDAO.merge(produto);
			
			//Trecho gerado na aula 229 upload de imagem para cópiar a imagem para um diretório padrão
//			Produto produtoRetorno = produtoDAO.mergeUpload(produto);
//			Path origem = Paths.get(produto.getCaminho()); //Origem do caminho
//			Path destino = Paths.get("C:/Users/Java/workspaceJava/uploadImagens/"+produtoRetorno.getCodigo()+".png"); //Destino
//			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			
			produto = new Produto();
			produtos = produtoDAO.listar();
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
			
			Messages.addGlobalInfo("Registro salvo com sucesso.");
		//} catch (RuntimeException | IOException erro) {
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o registro, erro: "+ erro);
			erro.printStackTrace();
		}		
	}
	
	public void excluir(ActionEvent evento){
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);
			
			produtos = produtoDAO.listar();
			
			Messages.addGlobalInfo("Registro removido com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao excluir o registro, erro: "+ erro);
			erro.printStackTrace();
		}		
	}
	
	public void editar(ActionEvent evento){
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			//Instânciando o estado para mostrar o campo list do botão novo.
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
			
			Messages.addGlobalInfo("Registro alterado com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao editar o registro, erro: "+ erro);
			erro.printStackTrace();
		}		
	}
	
	public void upload(FileUploadEvent evento){
		try {
			UploadedFile arquivoUpload =  evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			
			//Faz a cópia do arquivo upado para a pasta temporária do sistema operacional
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			
			produto.setCaminho(arquivoTemp.toString());
		
			Messages.addGlobalInfo("Caminho: "+produto.getCaminho());
			System.out.println("Caminho: "+produto.getCaminho());
			
			Messages.addGlobalInfo("Upload realizado com sucesso");
		} catch (IOException erro) {
			Messages.addGlobalError("Erro ao realizar upload do arquivo, erro: "+ erro);
			erro.printStackTrace();
		}
	}
	
	public void upload2(FileUploadEvent evento) {
			System.out.println("Teste componente Upload");
	}
}
