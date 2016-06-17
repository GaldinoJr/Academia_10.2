package com.example.galdino.academia_102.Core.Impl.SqlDAO;


import android.content.Context;
import android.text.TextUtils;

import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Exercicio;
import com.example.galdino.academia_102.Dominio.GrupoMuscular;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SQLGrupoMuscular extends AbsSQL{
	private static final String Col_cd_grupo = "cd_grupo";
	private static final String Col_ds_grupo = "ds_grupo";
	//private static final String[] colunas = {Col_ds_grupo};
	private static final String[] colunasBusca = {Col_cd_grupo,Col_ds_grupo};
	//private SQL db;
	
	//private Map<String, String> mapGrupo;
	private String sId;
	private GrupoMuscular grupoMuscular;
	
	protected void iniciar()
	{
		DATABASE_NAME = "GuiaAcademiaDB";
		nomeTabela = "tb_grupo";
		sqlCriarTabela = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " ( " +
				Col_cd_grupo + " INTEGER PRIMARY KEY, " +
				Col_ds_grupo + " TEXT )";
	}

	@Override
	public void criarColunas()
	{
		colunas = new LinkedList<String>();
		colunas.add(Col_ds_grupo);
	}

	public SQLGrupoMuscular(Context context){
		iniciar();
		//db  = new SQL(context, DATABASE_NAME, nm_tabela,colunas, sqlCriarTabela ); // GANHO DE PERFORMANCE NO CÓDIGO ORIGINAL
		db  = SQL.getInstance(context, DATABASE_NAME );
		criarColunas();
		db.popularInfo(nomeTabela, colunas, sqlCriarTabela);
	}

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		try {
			grupoMuscular =  (GrupoMuscular)entidade;
			mapSql = new HashMap<String, String>();
			
			mapSql.put(Col_ds_grupo, grupoMuscular.getNome());
//			removeCamposVazios();
//			long id = db.addRegistro(mapSql);
			long id = Incluir();
			//db.close();
			grupoMuscular.setID(String.valueOf(id));
			return grupoMuscular;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void alterar(EntidadeDominio entidade) {

	}

	@Override
	public void excluir(EntidadeDominio entidade) {

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade)
	{
		int i;
		grupoMuscular =  (GrupoMuscular)entidade;
		try
		{
			String query = "SELECT " + Col_cd_grupo;
			for(i = 1; i < colunasBusca.length; i++)
			{
				query += ", " + colunasBusca[i];
			}
			query += " FROM " + nomeTabela + " WHERE 1 = 1";
			if (!TextUtils.isEmpty(grupoMuscular.getID()))
				query += " AND " + Col_cd_grupo + " = '" + grupoMuscular.getID() + "'";

			if (!TextUtils.isEmpty(grupoMuscular.getNome()))
				query += " AND " + Col_ds_grupo + " = '" + grupoMuscular.getNome() + "'";

			listSql = new ArrayList<EntidadeDominio>();

			listMapSql = new LinkedList<Map<String, String>>(); // talvez seja redundante, testar e tirar se for*****
			listMapSql = db.pesquisarComSelect(query, colunasBusca);
			//db.close();
			for(i = 0; i< listMapSql.size();i++)
			{
				GrupoMuscular gm = new GrupoMuscular();
				// ******************* TEM QUE SER A MESMA SEQUENCIA DA LISTA(colunasBusca)***********************
				//Col_cd_grupo,Col_ds_grupo
				gm.setID(listMapSql.get(i).get(colunasBusca[0]));
				gm.setNome(listMapSql.get(i).get(colunasBusca[1]));

				listSql.add(gm);
			}
			if(listSql.size() > 0)
				return listSql;
			else
				return null;
		}
		catch(Exception e){ e.printStackTrace(); }

		return null;
	}
}
