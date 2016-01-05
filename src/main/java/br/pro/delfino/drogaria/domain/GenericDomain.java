package br.pro.delfino.drogaria.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//Essa notação não corresponderá há uma tabela mas que será utilizada pelas outras Class que irão herdar.
@SuppressWarnings("serial")
@MappedSuperclass
public class GenericDomain implements Serializable {
	/*
	 * @Id -> Quem Hedar essa Class o campo Id será a chave primária
	 * 
	 * @GeneratedValue notação que irá autoIncrement no banco Essa notação tem o
	 * parâmetro strategy onde as opções: GenerationType.AUTO -> Gera a chave
	 * primária automaticamente; GenerationType.IDENTITY -> O usuário irá
	 * digitar o valor da chave primária GenerationType.SEQUENCE e
	 * GenerationType.TABLE sáo casos especificos para os bancos Oracle e
	 * PostgreSQL.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;

	// Métodos get e set
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
}
