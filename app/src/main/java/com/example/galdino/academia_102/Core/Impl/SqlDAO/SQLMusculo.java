package com.example.galdino.academia_102.Core.Impl.SqlDAO;

import android.content.Context;
import android.text.TextUtils;

import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Musculo;
import com.example.galdino.academia_102.Dominio.Treino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Galdino on 27/02/2016.
 */
public class SQLMusculo  extends AbsSQL {
    private static final String Col_cd_musculo = "cd_musculo";
    private static final String Col_ds_musculo = "ds_musculo";
    //private static final String[] colunas = {Col_ds_musculo};
    private static final String[] colunasBusca = {Col_cd_musculo,Col_ds_musculo};
    //private SQL db;

    private Musculo musculo;
    @Override
    protected void iniciar() {
        DATABASE_NAME = "GuiaAcademiaBD";
        nomeTabela = "tb_musculo";
        sqlCriarTabela = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " ( " +
                Col_cd_musculo + " INTEGER PRIMARY KEY, " +
                Col_ds_musculo + " TEXT )";
    }

    public SQLMusculo(Context context)
    {
        iniciar();
        db  = SQL.getInstance(context, DATABASE_NAME );
        criarColunas();
        db.popularInfo(nomeTabela, colunas, sqlCriarTabela);
    }

    @Override
    public void criarColunas()
    {
        colunas = new LinkedList<String>();
        colunas.add(Col_ds_musculo);
    }

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        try {
            musculo =  (Musculo)entidade;
            mapSql = new HashMap<String, String>();

            mapSql.put(Col_ds_musculo, musculo.getNome());
            removeCamposVazios();
            long id = db.addRegistro(mapSql);
            //db.close();
            musculo.setID(String.valueOf(id));
            return musculo;
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
        musculo = (Musculo)entidade;
        try
        {
            String query = "SELECT " + Col_cd_musculo;
            for(i = 1; i < colunasBusca.length; i++)
            {
                query += ", " + colunasBusca[i];
            }
            query += " FROM " + nomeTabela + " WHERE 1 = 1";
            if (!TextUtils.isEmpty(musculo.getID()))
                query += " AND " + Col_cd_musculo + "= '" + musculo.getID() + "'";

            listSql = new ArrayList<EntidadeDominio>();

            listMapSql = new LinkedList<Map<String, String>>(); // talvez seja redundante, testar e tirar se for*****
            listMapSql = db.pesquisarComSelect(query, colunasBusca);
            //db.close();
            for(i = 0; i< listMapSql.size();i++)
            {
                Musculo m = new Musculo();
                // ******************* TEM QUE SER A MESMA SEQUENCIA DA LISTA(colunasBusca)***********************
                m.setID(listMapSql.get(i).get(colunasBusca[0]));
                m.setNome(listMapSql.get(i).get(colunasBusca[1]));

                listSql.add(m);
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
