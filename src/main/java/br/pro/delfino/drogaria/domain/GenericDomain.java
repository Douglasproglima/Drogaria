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
	
	//OmniFaces - converter="omnifaces.SelectItemsConverter" que ficará na view
	 @Override
	 public String toString() {
	     return String.format("%s[codigo=%d]", getClass().getSimpleName(), getCodigo());
	 }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericDomain other = (GenericDomain) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
