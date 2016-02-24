package com.example.galdino.academia_102.Dominio;

import android.content.Context;

import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Core.Aplicacao.Resultado;
import com.example.galdino.academia_102.Core.Impl.Controle.Session;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EntidadeDominio {
	public String DF_ID = "ID";
	private String ID;
	protected Map<String, String> map;
	protected EntidadeDominio entidade;
	// sets
	public void setID(String iD) {
		ID = iD;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	// gets
	public String getID() {
		return ID;
	}
	public Map<String, String> getMap() {
		return map;
	}

	public List<EntidadeDominio> operar(Context context, boolean fgSql, String operacao, EntidadeDominio entidade)
	{
		//map.put("operacao", operacao);          // indica a operação que está sendo realizada
		Session session = Session.getInstance();
		session.setNameInstanceClass(entidade.getClass().getName());
		if (fgSql)
			session.setContext(context);
		else
			session.setContext(null);
		//
		Controler controler = new Controler();
		List<EntidadeDominio> list = new LinkedList<EntidadeDominio>();
		Resultado resultado = controler.doPost(entidade, operacao);
		list = resultado.getEntidades();
		return list;
	}

	public static <T> List<T> converteEntidadeEmClasse(List<?> listEntidade, Class<T> classe)
	{
		List<T> listClasse = new ArrayList<T>();
		if(listEntidade != null) {
			if (listEntidade.size() > 0) {
				for (Object o : listEntidade) {
					if (classe.isInstance(o)) {
						listClasse.add(classe.cast(o));
					}
				}
				return listClasse;
			} else
				return null;
		}
		else
			return null;
	}
}
