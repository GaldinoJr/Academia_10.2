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

	protected void removeCamposVazios()
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
