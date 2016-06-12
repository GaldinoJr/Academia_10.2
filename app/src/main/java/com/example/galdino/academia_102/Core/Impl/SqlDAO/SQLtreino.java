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
	private static final String Col_ds_nome = "ds_nome";
	private static final String Col_ds_treino = "ds_treino";
	private static final String Col_cd_grupo = "f_cd_grupo";
	private static final String Col_fg_carga = "fg_carga";
	// Tipo Treino - indice
	// Definição   - 0
	// Força	   - 1
	// Hipertrofia - 2
	// Resistencia - 3
	private static final String Col_ind_tipo_treino = "ind_tipo_treino";
	// Nivel	 	 - indice
	// Iniciante 	 - 0
	// Intermediario - 1
	// Avançado 	 - 2
	private static final String Col_ind_nivel = "ind_nivel";
	private static final String Col_ds_nome_foto = "ds_nome_foto";
	// Feminino  - 0
	// Masculino - 1
	private static final String Col_ind_sexo = "ind_sexo";
	//private static final String[] colunas = {Col_ds_nome,Col_ds_treino, Col_fg_carga, Col_cd_grupo,Col_ind_tipo_treino,Col_ind_nivel,Col_ds_nome_foto};
	//private static final List<String> colunas1 = new {Col_ds_nome,Col_ds_treino, Col_fg_carga, Col_cd_grupo,Col_ind_tipo_treino,Col_ind_nivel,Col_ds_nome_foto};
	private static final String[] colunasBusca = {Col_cd_treino,Col_ds_nome,Col_ds_treino, Col_fg_carga, Col_cd_grupo,Col_ind_tipo_treino,Col_ind_nivel,Col_ds_nome_foto, Col_ind_sexo};

	private Context context;
	private Treino treino;

	protected void iniciar()
	{
		DATABASE_NAME = "GuiaAcademiaBD";
		nomeTabela = "tb_treino";
		sqlCriarTabela = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " ( " +
				Col_cd_treino + " INTEGER PRIMARY KEY, " +
				Col_ds_nome + " TEXT, " +
				Col_ds_treino + " TEXT, " +
				Col_fg_carga + " INTEGER, " +
				Col_cd_grupo + " INTEGER, " +
				Col_ind_tipo_treino + " INTEGER, " +
				Col_ind_nivel + " INTEGER, " +
				Col_ds_nome_foto + " TEXT, " +
				Col_ind_sexo + " INTEGER )";
	}

	@Override
	public void criarColunas()
	{
		colunas = new LinkedList<String>();
		colunas.add(Col_ds_nome);
		colunas.add(Col_ds_treino);
		colunas.add(Col_fg_carga);
		colunas.add(Col_cd_grupo);
		colunas.add(Col_ind_tipo_treino);
		colunas.add(Col_ind_nivel);
		colunas.add(Col_ds_nome_foto);
		colunas.add(Col_ind_sexo);
	}
	public SQLtreino(Context context){
		iniciar();
		this.context = context;
		//db  = new SQL(context, DATABASE_NAME, nomeTabela, colunas, sqlCriarTabela ); // GANHO DE PERFORMANCE NO CÓDIGO ORIGINAL
		db  = SQL.getInstance(context, DATABASE_NAME );
		criarColunas();
		db.popularInfo(nomeTabela, colunas, sqlCriarTabela);
	}

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		try
		{
			treino =  (Treino)entidade;
			mapSql = new HashMap<String, String>();

			mapSql.put(Col_ds_nome, String.valueOf(treino.getNome()));
			mapSql.put(Col_ds_treino, String.valueOf(treino.getDescricao()));
			mapSql.put(Col_fg_carga, String.valueOf(treino.getFgCarga()));
			mapSql.put(Col_cd_grupo, String.valueOf(treino.getIdGrupo()));
			mapSql.put(Col_ind_tipo_treino, String.valueOf(treino.getIndTipoTreino()));
			mapSql.put(Col_ind_nivel, String.valueOf(treino.getIndNivel()));
			mapSql.put(Col_ds_nome_foto, String.valueOf(treino.getDsNomeFoto()));
			mapSql.put(Col_ind_sexo, String.valueOf(treino.getIndSexo()));
			removeCamposVazios();
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

			//listMapSql = new LinkedList<Map<String, String>>(); // talvez seja redundante, testar e tirar se for*****
			listMapSql = db.pesquisarComSelect(query, colunasBusca);
			//db.close();
			for(i = 0; i< listMapSql.size();i++)
			{
				Treino t = new Treino();
				// ******************* TEM QUE SER A MESMA SEQUENCIA DA LISTA(colunasBusca)***********************
				t.setID(listMapSql.get(i).get(colunasBusca[0]));
				t.setNome(listMapSql.get(i).get(colunasBusca[1]));
				t.setDescricao(listMapSql.get(i).get(colunasBusca[2]));
				if(listMapSql.get(i).get(colunasBusca[3]) != null)
					t.setFgCarga(Integer.parseInt(listMapSql.get(i).get(colunasBusca[3])));
				if(listMapSql.get(i).get(colunasBusca[4]) != null)
					t.setIdGrupo(Integer.parseInt(listMapSql.get(i).get(colunasBusca[4])));
				if(listMapSql.get(i).get(colunasBusca[5]) != null)
					t.setIndTipoTreino(Integer.parseInt(listMapSql.get(i).get(colunasBusca[5])));
				if(listMapSql.get(i).get(colunasBusca[6]) != null)
					t.setIndNivel(Integer.parseInt(listMapSql.get(i).get(colunasBusca[6])));
				t.setDsNomeFoto(listMapSql.get(i).get(colunasBusca[7]));
				if(listMapSql.get(i).get(colunasBusca[8]) != null)
					t.setIndSexo(Integer.parseInt(listMapSql.get(i).get(colunasBusca[8])));
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
		if (!TextUtils.isEmpty(t.getNome()))
			clausula += " AND " + Col_ds_nome + "= '" + t.getNome() + "'";
		if (t.getIdGrupo() != null)
			clausula += " AND " + Col_cd_grupo + "= '" + t.getIdGrupo() + "'";
		if (t.getFgCarga() != null)
			if(t.getFgCarga() != 3)
				clausula += " AND " + Col_fg_carga + "= '" + t.getFgCarga() + "'";
		if (t.getIndTipoTreino() != null)
			clausula += " AND " + Col_ind_tipo_treino + "= '" + t.getIndTipoTreino() + "'";
		if (t.getIndNivel() != null)
			clausula += " AND " + Col_ind_nivel + "= '" + t.getIndNivel() + "'";

		String codigosIndTipoTreino = montarClausulaComList(t.getListaCodigosObjParaBusca());
		if (codigosIndTipoTreino != null)
			clausula += " AND " + Col_ind_tipo_treino + " IN (" + codigosIndTipoTreino + ")";

		String codigosIndNivel = montarClausulaComList(t.getListaCodigosNivelParaBusca());
		if (codigosIndNivel != null)
			clausula += " AND " + Col_ind_nivel + " IN (" +codigosIndNivel + ")";

		if (t.getDsNomeFoto() != null)
			clausula += " AND " + Col_ds_nome_foto + "= '" + t.getDsNomeFoto() + "'";

		if (t.getIndSexo() != null)
			if(t.getIndSexo() != 3)
			clausula += " AND " + Col_ind_sexo + "= '" + t.getIndSexo() + "'";

		return clausula;
	}
	private String montarClausulaComList(List<String> lista)
	{
		String codigos = "";
		if(lista == null)
			return null;
		int tamanho = lista.size();
		if(tamanho == 0)
			return null;
		for(int i = 0; i < tamanho; i++)
		{
			codigos += lista.get(i);
			if(i < (tamanho-1)) // não é ultimo registro da lista?
				codigos += ", ";
		}
		return codigos;
	}
}
