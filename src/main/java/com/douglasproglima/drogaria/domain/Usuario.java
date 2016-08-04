package com.douglasproglima.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain{
	@OneToOne
	@JoinColumn(name = "codPessoa", nullable = false, foreignKey = @ForeignKey(name = "FK_UsuarioXPessoa"))
	private Pessoa pessoa;
	
	@Column(nullable = false, length = 20)
	private String usuario;
	
	@Column(nullable = false)
	private Boolean ativo;
	
	@Column(length = 32, nullable = false)
	private String senha;
	
	@Transient
	private String senhaSemCriptografia;
	
	@Column(nullable = false, length = 1)
	private Character tipo;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	@Transient
	public String getativoFormatado() {
		String ativoFormatado = null;
		if (ativo) {
			ativoFormatado = "Sim";
		}else{
			ativoFormatado = "Não";
		}
		
		return ativoFormatado;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getSenha() {
		return senha;
	}

	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}
	
	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Character getTipo() {
		return tipo;
	}
	
	@Transient
	public String getTipoFormatado() {
		String tipoFormatado = null;
		if (tipo == 'A') {
			tipoFormatado = "Administrador";
		} else if(tipo == 'B'){
			tipoFormatado = "Balconista";
		} else if(tipo == 'G'){
			tipoFormatado = "Gerente";
		}
		
		return tipoFormatado;
	}
	
	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}
}