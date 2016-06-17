package com.example.galdino.academia_102.Core.Impl.SqlDAO;

import com.example.galdino.academia_102.Core.IDAO;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AbsSQL implements IDAO {
	protected SQL db;
	protected String DATABASE_NAME;
	protected String nomeTabela; // sempre colocar IF NOT EXISTS na criacao da tabela
	protected String sqlCriarTabela;
	//protected String[] colunas; // colunas da tabela fora o id
	protected List<String> colunas; // colunas da tabela fora o id
	protected String[] colunasBusca;
	protected abstract void iniciar(); // teste
	protected HashMap<String, String> mapSql;
	protected List<EntidadeDominio> listSql;
	protected List<Map<String, String>> listMapSql;

	protected long Incluir()
	{
		removeCamposVazios();
		long id = db.addRegistro(mapSql);
		return id;
	}

	protected long Alterar(String where, String[] valoresWhere)
	{
		removeCamposVazios();
		where = removeAndComeco(where);
		long id = db.alterarComClausula(mapSql, where, valoresWhere);
		return id;
	}
	private String removeAndComeco(String where)
	{
		String retorno = "";
		int tamanho =  where.length();
		if(where != null && tamanho > 2)
		{
			String aux = where.substring(1, 4);
			if (aux.toUpperCase().equals("AND"))
				retorno = where.substring(5,tamanho);
			else
				retorno = where;
		}
		return retorno;
	}
	private void removeCamposVazios()
	{
		for(int i = 0; i < colunas.size(); i++)
		{
			if(mapSql.get(colunas.get(i)) == null || mapSql.get(colunas.get(i)).equals("null"))
			{
				mapSql.remove(colunas.get(i));
				colunas.remove(i);
				i--;
			}
		}
		if(db != null)
			db.setColunas(colunas);
	}
}
