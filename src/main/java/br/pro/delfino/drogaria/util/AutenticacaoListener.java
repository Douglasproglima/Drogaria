package br.pro.delfino.drogaria.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.pro.delfino.drogaria.bean.AutenticacaoBean;
import br.pro.delfino.drogaria.domain.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent event) {
		//Retorna a página atual.
		String paginaAtual =  Faces.getViewId();
		
		//Verifica se a página atual é referente a página de autenticação
		boolean ePaginaDeAutenticacao = paginaAtual.contains("autenticacao.xhtml");
		
		//Se for uma página privada verifica se existe usuário logado
		if (!ePaginaDeAutenticacao) {
			//É necessário acessar o Bean Autenticar e pegar o atributo usuarioLogado, neste caso estou utilizando
			//o omnifaces com o método getSessionAttribute() que possibilita pegar os dados da sessãod o framework.
			//Passar o mesmo nome utilizado value da página .xhtml - exemplo {#autenticacaoBean}
			AutenticacaoBean autenticacaoBean =  Faces.getSessionAttribute("autenticacaoBean");			
		
			//Se o autenticação for null, faz uma navegação utilizando o omnifaces para direcionar o usuário para autenticação
			if (autenticacaoBean == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			//Se o usuário for igual a null, direcionar o usuário para tela de autenticação.
			if (usuario == null) {
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
		
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		//
		return PhaseId.RESTORE_VIEW;
	}

}
