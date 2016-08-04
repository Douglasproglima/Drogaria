package com.douglasproglima.drogaria.bean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

//@RequestScoped estou utilizando essa anotação pois a imagem será carregada a cada clique
@ManagedBean
@RequestScoped
public class ImagemBean {
	
	//Seá utilizado no componente p:graphicImage para passar o caminho que está na class ProdutoBean()
	//Será passado o caminho atravpes de parâmetros
	@ManagedProperty("#{param.caminho}")
	private String caminho;
	private StreamedContent foto;
	
	public String getCaminho() {
		return caminho;
	}
	
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
	
	public StreamedContent getFoto() throws IOException {
		//Quando não tiver foto, será utilizado uma foto em branco com apenas 1px
		if (caminho == null || caminho.isEmpty()) {

			Path caminhoFotoPadrao = Paths.get("D:/AmbienteDesenvolvimento/Java/Workspace/uploadImagens/branco.png");
			InputStream streamFluxo = Files.newInputStream(caminhoFotoPadrao);
			foto =  new DefaultStreamedContent(streamFluxo);
		}else{
			Path caminhoFotoPadrao = Paths.get(caminho);
			InputStream streamFluxo = Files.newInputStream(caminhoFotoPadrao);
			foto =  new DefaultStreamedContent(streamFluxo);
		}
		
		return foto;
	}
	
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
}
