package com.example.galdino.academia_102.Core.Impl.SqlDAO;


import android.content.Context;
import android.text.TextUtils;

import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.Dominio.TreinoExercicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SQLtreino extends AbsSQL{
	private static final String Col_cd_treino = "cd_treino";
	private static final String Col_ds_treino = "ds_treino";
	private static final String Col_cd_grupo = "f_cd_grupo";
	private static final String[] colunas = {Col_ds_treino, Col_cd_grupo};
	private static final String[] colunasBusca = {Col_cd_treino,Col_ds_treino, Col_cd_grupo};
	private SQL db;
	private Context context;
	private Treino treino;

	protected void iniciar()
	{
		DATABASE_NAME = "GuiaAcademiaBD";
		nomeTabela = "tb_treino";
		sqlCriarTabela = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " ( " +
				Col_cd_treino + " INTEGER PRIMARY KEY, " +
				Col_ds_treino + " TEXT, " +
				Col_cd_grupo + " INTEGER )";
	}
	
	public SQLtreino(Context context){
		iniciar();
		this.context = context;
		//db  = new SQL(context, DATABASE_NAME, nomeTabela, colunas, sqlCriarTabela ); // GANHO DE PERFORMANCE NO CÓDIGO ORIGINAL
		db  = SQL.getInstance(context, DATABASE_NAME );
		db.popularInfo(nomeTabela, colunas, sqlCriarTabela);
	}

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		try {
			treino =  (Treino)entidade;
			mapSql = new HashMap<String, String>();

			mapSql.put(Col_ds_treino, String.valueOf(treino.getNome()));
			mapSql.put(Col_cd_grupo, String.valueOf(treino.getIdGrupo()));

			long id = db.addRegistro(mapSql);
			//db.close();
			treino.setID(String.valueOf(id));
			return treino;
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
		treino = (Treino)entidade;
		if(!TextUtils.isEmpty(treino.getID()))
		{
			db.deletarRegistro(treino.getID(),Col_cd_treino); // Quando for usar algo de outra base, ou abrir uma conexão novamente
			TreinoExercicio treinoExercicio = new TreinoExercicio();
			treinoExercicio.setIdTreino(Integer.parseInt(treino.getID()));
			treinoExercicio.operar(context,true, Controler.DF_EXCLUIR,treinoExercicio);
		}
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		int i;
		treino = (Treino)entidade;
		try
		{
			String query = "SELECT " + Col_cd_treino;
			for(i = 1; i < colunasBusca.length; i++)
			{
				query += ", " + colunasBusca[i];
			}
			query += " FROM " + nomeTabela + montarClausulaWhere(treino);

			listSql = new ArrayList<EntidadeDominio>();

			listMapSql = new LinkedList<Map<String, String>>(); // talvez seja redundante, testar e tirar se for*****
			listMapSql = db.pesquisarComSelect(query, colunasBusca);
			//db.close();
			for(i = 0; i< listMapSql.size();i++)
			{
				Treino t = new Treino();
				// ******************* TEM QUE SER A MESMA SEQUENCIA DA LISTA(colunasBusca)***********************
				t.setID(listMapSql.get(i).get(colunasBusca[0]));
				t.setNome(listMapSql.get(i).get(colunasBusca[1]));
				if(listMapSql.get(i).get(colunasBusca[2]) != null && !listMapSql.get(i).get(colunasBusca[2]).equals("null"))
					t.setIdGrupo(Integer.parseInt(listMapSql.get(i).get(colunasBusca[2])));

				listSql.add(t);
			}
			if(listSql.size() > 0)
				return listSql;
			else
				return null;
		}
		catch(Exception e){ e.printStackTrace(); }

		return null;
	}
	private String montarClausulaWhere(Treino t)
	{
		String clausula = " WHERE 1 = 1";
		if (!TextUtils.isEmpty(t.getID()))
			clausula += " AND " + Col_cd_treino + "= '" + t.getID() + "'";
		if (t.getIdGrupo() != null)
			clausula += " AND " + Col_cd_grupo + "= '" + t.getIdGrupo() + "'";
		return clausula;
	}
}
