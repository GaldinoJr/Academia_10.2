package com.example.galdino.academia_102.Core.Impl.SqlDAO;

import android.content.Context;
import android.text.TextUtils;

import com.example.galdino.academia_102.Dominio.Configuracao;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.TreinoExercicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Galdino on 28/02/2016.
 */
public class SQLConfiguracao extends AbsSQL {
    private static final String Col_cd_configuracao = "cd_configuracao";
    private static final String Col_fg_banco_criado = "fg_banco_criado";
    private static final String[] colunas = {Col_fg_banco_criado};
    private static final String[] colunasBusca = {Col_cd_configuracao,Col_fg_banco_criado};
    private SQL db;

    private Configuracao configuracao;
    @Override
    protected void iniciar() {
        DATABASE_NAME = "GuiaAcademiaBD";
        nomeTabela = "tb_configuracao";
        sqlCriarTabela = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " ( " +
                Col_cd_configuracao + " INTEGER PRIMARY KEY, " +
                Col_fg_banco_criado + " INTEGER )";
    }

    public SQLConfiguracao(Context context)
    {
        iniciar();
        db  = SQL.getInstance(context, DATABASE_NAME );
        db.popularInfo(nomeTabela, colunas, sqlCriarTabela);
    }

    @Override
    public void salvar(EntidadeDominio entidade) {
        try {
            configuracao =  (Configuracao)entidade;
            mapSql = new HashMap<String, String>();

            mapSql.put(Col_fg_banco_criado, String.valueOf(configuracao.getFgBancoCriado()));
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
    public List<EntidadeDominio> consultar(EntidadeDominio entidade)
    {
        int i;
        configuracao = (Configuracao)entidade;
        try
        {
            String query = "SELECT " + Col_cd_configuracao;
            for(i = 1; i < colunasBusca.length; i++)
            {
                query += ", " + colunasBusca[i];
            }
            query += " FROM " + nomeTabela + " WHERE 1 = 1";
            if (!TextUtils.isEmpty(configuracao.getID()))
                query += " AND " + Col_cd_configuracao + " = '" + configuracao.getID() + "'";

            listSql = new ArrayList<EntidadeDominio>();

            listMapSql = new LinkedList<Map<String, String>>(); // talvez seja redundante, testar e tirar se for*****
            listMapSql = db.pesquisarComSelect(query, colunasBusca);
            //db.close();
            for(i = 0; i< listMapSql.size();i++)
            {
                Configuracao c = new Configuracao();
                // ******************* TEM QUE SER A MESMA SEQUENCIA DA LISTA(colunasBusca)***********************
                // Col_cd_configuracao,Col_fg_banco_criado
                c.setID(listMapSql.get(i).get(colunasBusca[0]));
                c.setFgBancoCriado(Integer.parseInt(listMapSql.get(i).get(colunasBusca[1])));

                listSql.add(c);
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
