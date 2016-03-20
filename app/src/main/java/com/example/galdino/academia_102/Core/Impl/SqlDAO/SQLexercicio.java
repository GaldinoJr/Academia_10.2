package com.example.galdino.academia_102.Core.Impl.SqlDAO;

import android.content.Context;
import android.text.TextUtils;


import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Exercicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SQLexercicio extends AbsSQL{
	
	private static final String Col_cd_exercicio = "cd_exercicio";
	private static final String Col_ds_nome_exercicio = "ds_nome_exercicio";
	private static final String Col_ds_nome_logico_gif_exercicio = "ds_nome_logico_gif_exercicio";
	private static final String Col_ds_nome_logico_foto_exercicio = "ds_nome_logico_foto_exercicio";
	private static final String Col_ds_exercicio = "ds_exercicio";
	private static final String Col_cd_grupo = "f_cd_grupo";
	private static final String[] colunas = {Col_ds_nome_exercicio, Col_ds_nome_logico_gif_exercicio,Col_ds_nome_logico_foto_exercicio, Col_ds_exercicio,Col_cd_grupo};
	private static final String[] colunasBusca = {Col_cd_exercicio, Col_ds_nome_exercicio, Col_ds_nome_logico_gif_exercicio,Col_ds_nome_logico_foto_exercicio, Col_ds_exercicio,Col_cd_grupo};
	private SQL db;
	
	private Map<String, String> mapExercicio;
	private String sId;
	private Exercicio exercicio;
	protected void iniciar()
	{
		DATABASE_NAME = "GuiaAcademiaDB";
		nomeTabela	 = "tb_exercicio";
		sqlCriarTabela = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " ( " +
				Col_cd_exercicio + " INTEGER PRIMARY KEY, " +
				Col_ds_nome_exercicio + " TEXT, "+
				Col_ds_nome_logico_gif_exercicio + " TEXT, "+
				Col_ds_nome_logico_foto_exercicio + " TEXT, " +
				Col_ds_exercicio + " TEXT, "+
				Col_cd_grupo + " INTEGER )";// com FOREIGN KEY não funciona
	}

	public SQLexercicio(Context context){
		iniciar();
		//db  = new SQL(context, DATABASE_NAME, nm_tabela,colunas, sqlCriarTabela ); // GANHO DE PERFORMANCE NO CÓDIGO ORIGINAL
		db  = SQL.getInstance(context, DATABASE_NAME );
		db.popularInfo( nomeTabela, colunas, sqlCriarTabela);
	}

	
//	public Exercicio pesquisarExercicio(int id)
//	{
//		String[] colunasBusca = {Col_cd_exercicio, Col_ds_exercicio, Col_cd_grupo};
//		sId = String.valueOf(id);
//		Map<String, String> mapDados = new HashMap<String, String>();
//		mapDados = db.buscarRegistro(Col_cd_exercicio,sId, colunasBusca);
//		Exercicio exercicio = new Exercicio();
//		if(mapDados == null) // não encontrou o exercício?
//			return exercicio = null; // retorna indicando que o exercício não foi encontrado
//
//		exercicio.setID(mapDados.get(Col_cd_exercicio));
//		exercicio.setNome(mapDados.get(Col_ds_exercicio));
//		exercicio.setIdGrupo(mapDados.get(Col_cd_grupo));
//	    return exercicio;
//
//	}

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		try {
			exercicio =  (Exercicio)entidade;
			mapSql = new HashMap<String, String>();

			mapSql.put(Col_ds_nome_exercicio, exercicio.getNome());
			mapSql.put(Col_ds_nome_logico_gif_exercicio, exercicio.getNomeLogico());
			mapSql.put(Col_ds_nome_logico_foto_exercicio, exercicio.getNomeLogicoFoto());
			mapSql.put(Col_ds_exercicio, exercicio.getDescricao());
			mapSql.put(Col_cd_grupo, String.valueOf(exercicio.getIdGrupo()));
			long id = db.addRegistro(mapSql);
			exercicio.setID(String.valueOf(id));
			//db.close();
			return exercicio;
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
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		int i;
		exercicio =  (Exercicio)entidade;
		try
		{
			String query = "SELECT " + Col_cd_exercicio;
			for(i = 1; i < colunasBusca.length; i++)
			{
				query += ", " + colunasBusca[i];
			}
			query += " FROM " + nomeTabela + " WHERE 1 = 1";
			if (!TextUtils.isEmpty(exercicio.getID()))
				query += " AND " + Col_cd_exercicio + " = '" + exercicio.getID() + "'";
			if (exercicio.getIdGrupo() != null)
				query += " AND " + Col_cd_grupo + " = '" + exercicio.getIdGrupo() + "'";

			query += " ORDER BY " + Col_ds_nome_exercicio + " ASC";
			listSql = new ArrayList<EntidadeDominio>();

			listMapSql = new LinkedList<Map<String, String>>(); // talvez seja redundante, testar e tirar se for*****
			listMapSql = db.pesquisarComSelect(query, colunasBusca);
			//db.close();
			for(i = 0; i< listMapSql.size();i++)
			{
				Exercicio e = new Exercicio();
				// ******************* TEM QUE SER A MESMA SEQUENCIA DA LISTA(colunasBusca)***********************
				//Col_cd_exercicio, Col_ds_nome_exercicio, Col_ds_nome_logico_gif_exercicio,Col_ds_nome_logico_foto_exercicio, Col_ds_exercicio,Col_cd_grupo
				e.setID(listMapSql.get(i).get(colunasBusca[0]));
				e.setNome(listMapSql.get(i).get(colunasBusca[1]));
				e.setNomeLogico(listMapSql.get(i).get(colunasBusca[2]));
				e.setNomeLogicoFoto(listMapSql.get(i).get(colunasBusca[3]));
				e.setDescricao(listMapSql.get(i).get(colunasBusca[4]));
				e.setIdGrupo(Integer.parseInt(listMapSql.get(i).get(colunasBusca[5])));

				listSql.add(e);
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
