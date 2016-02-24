package com.example.galdino.academia_102.Core.Impl.SqlDAO;

import com.example.galdino.academia_102.Core.IDAO;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;

import java.util.List;
import java.util.Map;

public abstract class AbsSQL implements IDAO {
	protected String DATABASE_NAME;
	protected String nomeTabela; // sempre colocar IF NOT EXISTS na criacao da tabela
	protected String sqlCriarTabela;
	protected String[] colunas; // colunas da tabela fora o id
	protected String[] colunasBusca;
	protected abstract void iniciar(); // teste
	protected Map<String, String> mapSql;
	protected List<EntidadeDominio> listSql;
	protected List<Map<String, String>> listMapSql;
}
