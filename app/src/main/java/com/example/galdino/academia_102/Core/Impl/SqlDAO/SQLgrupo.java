package com.example.galdino.academia_102.Core.Impl.SqlDAO;


import android.content.Context;

import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.GrupoMuscular;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLgrupo extends AbsSQL{
	private static final String Col_cd_grupo = "cd_grupo";
	private static final String Col_ds_grupo = "ds_grupo";
	private static final String[] colunas = {Col_ds_grupo};
	private static final String[] colunasBusca = {Col_cd_grupo,Col_ds_grupo};
	private SQL db;
	
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

	public SQLgrupo(Context context){
		iniciar();
		//db  = new SQL(context, DATABASE_NAME, nm_tabela,colunas, sqlCriarTabela ); // GANHO DE PERFORMANCE NO CÓDIGO ORIGINAL
		db  = SQL.getInstance(context, DATABASE_NAME );
		db.popularInfo(nomeTabela, colunas, sqlCriarTabela);
	}
	
//	public void IncluirGrupo(String nome)
//	{
//		mapGrupo = new HashMap<String, String>();
//		mapGrupo.put(Col_ds_grupo, nome);
//		db.addRegistro(mapGrupo);
//	}
	
	public GrupoMuscular pesquisarGrupo(int id)
	{
		String[] colunasBusca = {Col_cd_grupo, Col_ds_grupo};
		sId = String.valueOf(id);
		Map<String, String> mapDados = new HashMap<String, String>();
		mapDados = db.buscarRegistro(Col_cd_grupo,sId, colunasBusca);
		GrupoMuscular grupoMuscular = new GrupoMuscular();
		if(mapDados == null) // não encontrou o grupo?
			return grupoMuscular = null; // retorna indicando que o grupo não foi encontrado
		
		grupoMuscular.setID(mapDados.get(Col_cd_grupo));
		grupoMuscular.setNome(mapDados.get(Col_ds_grupo));
	    return grupoMuscular;

	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		try {
			grupoMuscular =  (GrupoMuscular)entidade;
			mapSql = new HashMap<String, String>();
			
			mapSql.put(Col_ds_grupo, String.valueOf(grupoMuscular.getNome()));
			db.addRegistro(mapSql);
			//db.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
		return null;
	}
}
