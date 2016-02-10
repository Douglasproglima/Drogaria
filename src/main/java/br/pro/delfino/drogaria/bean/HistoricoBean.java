package br.pro.delfino.drogaria.bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;

import br.pro.delfino.drogaria.dao.HistoricoDAO;
import br.pro.delfino.drogaria.dao.ProdutoDAO;
import br.pro.delfino.drogaria.domain.Historico;
import br.pro.delfino.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class HistoricoBean implements Serializable{
	private Historico historico;
	private Produto produto;
	private Boolean exibePainelDados;
	
	public Historico getHistorico() {
		return historico;
	}
	
	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Boolean getExibePainelDados() {
		return exibePainelDados;
	}
	
	public void setExibePainelDados(Boolean exibePainelDados) {
		this.exibePainelDados = exibePainelDados;
	}
	
	//Para chamar o método quando a tela estiver sendo carregada basta criar o @PostConstruct
	@PostConstruct
	public void novo(){
		historico =  new Historico();
		produto = new Produto();
		exibePainelDados = false;
	}
	
	public void buscar(){
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto resultado =  produtoDAO.buscar(produto.getCodigo());
			
			if (resultado ==  null) {
				exibePainelDados = false;
				Messages.addGlobalWarn("Não foi encontrado o registro de código: "+ produto.getCodigo());
			} else {
				exibePainelDados = true;
				produto = resultado;				
			}
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao pesquisar pelo registro informado. Erro: "+ erro);
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			historico.setDataHorario(new Date());
			historico.setProduto(produto);
			
			HistoricoDAO historicoDAO = new HistoricoDAO();
			historicoDAO.salvar(historico);
			
			Messages.addGlobalInfo("Registro salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o registro. Erro: "+ erro);
			erro.printStackTrace();
		}
	}
}
