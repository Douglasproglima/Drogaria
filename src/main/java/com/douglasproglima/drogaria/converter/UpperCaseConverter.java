package com.douglasproglima.drogaria.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

//@FacesConverter("Nome do objeto que será instânciado")
@FacesConverter("upperCaseConverter")
public class UpperCaseConverter implements Converter{

	//getAsObject(Contexto do JSF, Nome do componente que disparou, valor que foi enviado)
	@Override
	public Object getAsObject(FacesContext contexto, UIComponent componente, String valor) {
		if (valor ==  null) {
			return null;
		}else{
			return valor.toUpperCase();
		}
	}

	@Override
	public String getAsString(FacesContext contexto, UIComponent componente, Object valor) {
		if (valor == null) {
			return "";
		} else {
			return valor.toString();
		}
	}
	
}
