package com.example.galdino.academia_102.Core.Impl.SqlDAO;


import android.content.Context;
import android.text.TextUtils;

import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Treino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SQLtreino extends AbsSQL{
	private static final String Col_cd_treino = "cd_treino";
	private static final String Col_ds_treino = "ds_treino";
	private static final String[] colunas = {Col_ds_treino};
	private static final String[] colunasBusca = {Col_cd_treino,Col_ds_treino};
	private SQL db;
	
	private Map<String, String> mapTreino;
	private String sId;
	private Treino treino;

	protected void iniciar()
	{
		DATABASE_NAME = "GuiaAcademiaBD";
		nomeTabela = "tb_treino";
		sqlCriarTabela = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " ( " +
				Col_cd_treino + " INTEGER PRIMARY KEY, " +
				Col_ds_treino + " TEXT )";
	}
	
	public SQLtreino(Context context){
		iniciar();
		//db  = new SQL(context, DATABASE_NAME, nomeTabela, colunas, sqlCriarTabela ); // GANHO DE PERFORMANCE NO CÓDIGO ORIGINAL
		db  = SQL.getInstance(context, DATABASE_NAME );
		db.popularInfo(nomeTabela, colunas, sqlCriarTabela);
	}
	
//	public void IncluirTreino(String nome)
//	{
//		mapTreino = new HashMap<String, String>();
//		mapTreino.put(Col_ds_treino, nome);
//		db.addRegistro(mapTreino);
//	}
//	
//	public Treino pesquisarTreino(int id)
//	{
//		String[] colunasBusca = {Col_cd_treino, Col_ds_treino};
//		sId = String.valueOf(id);
//		Map<String, String> mapDados = new HashMap<String, String>();
//		mapDados = db.buscarRegistro(Col_cd_treino,sId, colunasBusca);
//		Treino treino = new Treino();
//		if(mapDados == null) // não encontrou o treino?
//			return treino = null; // retorna indicando que o treino não foi encontrado
//		
//		treino.setID(mapDados.get(Col_cd_treino));
//		treino.setNome(mapDados.get(Col_ds_treino));
//	    return treino;
//
//	}
	
//	public List<Treino> pesquisarTodosTreinos()
//	{
//		List<Treino> ltreino = new LinkedList<Treino>();
//		Treino treino;
//		String[] colunasBusca = {Col_cd_treino, Col_ds_treino};
//		 List<Map<String, String>> LMtreinos = new LinkedList<Map<String, String>>();
//		 LMtreinos = db.buscarTodosRegistros(colunasBusca);
//		 for(int i = 0; i< LMtreinos.size();i++)
//		 {
//			treino = new Treino();
//			treino.setID(LMtreinos.get(i).get(Col_cd_treino));
//			treino.setNome(LMtreinos.get(i).get(Col_ds_treino));
//			ltreino.add(treino);
//		 }
//		 return ltreino;
//	}

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		try {
			treino =  (Treino)entidade;
			mapSql = new HashMap<String, String>();

			mapSql.put(Col_ds_treino, String.valueOf(treino.getNome()));
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
		if(!TextUtils.isEmpty(treino.getID())) {
			db.deletarRegistro(treino.getID(),Col_cd_treino);
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
		return clausula;
	}
}
