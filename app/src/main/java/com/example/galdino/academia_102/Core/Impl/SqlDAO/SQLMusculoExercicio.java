package com.example.galdino.academia_102.Core.Impl.SqlDAO;

import android.content.Context;
import android.text.TextUtils;

import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Musculo;
import com.example.galdino.academia_102.Dominio.MusculoExercicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Galdino on 27/02/2016.
 */
public class SQLMusculoExercicio  extends AbsSQL {
    private static final String Col_cd_musculo_exercicio = "cd_musculo_exercicio";
    private static final String Col_cd_musculo = "f_cd_musculo";
    private static final String Col_cd_exercicio = "f_cd_exercicio";
    private static final String Col_ind_primario_secundario = "ind_primario_secundario";
    //private static final String[] colunas = {Col_cd_musculo,Col_cd_exercicio,Col_ind_primario_secundario};
    private static final String[] colunasBusca = {Col_cd_musculo_exercicio,Col_cd_musculo,Col_cd_exercicio,Col_ind_primario_secundario};
    //private SQL db;

    private MusculoExercicio musculoExercicio;
    @Override
    protected void iniciar() {
        DATABASE_NAME = "GuiaAcademiaBD";
        nomeTabela = "tb_musculo_exercicio";
        sqlCriarTabela = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " ( " +
                Col_cd_musculo_exercicio + " INTEGER PRIMARY KEY, " +
                Col_cd_musculo + " INTEGER FOREIGN KEY, "+
                Col_cd_exercicio + " INTEGER, "+ //FOREIGN KEY
                Col_ind_primario_secundario + " INTEGER )";
    }

    public SQLMusculoExercicio(Context context)
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
        colunas.add(Col_cd_musculo);
        colunas.add(Col_cd_exercicio);
        colunas.add(Col_ind_primario_secundario);
    }

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        try {
            musculoExercicio =  (MusculoExercicio)entidade;
            mapSql = new HashMap<String, String>();

            mapSql.put(Col_cd_musculo, String.valueOf(musculoExercicio.getIdMusculo()));
            mapSql.put(Col_cd_exercicio, String.valueOf(musculoExercicio.getIdExercicio()));
            mapSql.put(Col_ind_primario_secundario, String.valueOf(musculoExercicio.getIndPrimarioSecundario()));
            removeCamposVazios();
            long id = db.addRegistro(mapSql);
            //db.close();
            musculoExercicio.setID(String.valueOf(id));
            return musculoExercicio;
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
    public List<EntidadeDominio> consultar(EntidadeDominio entidade)
    {
        int i;
        musculoExercicio = (MusculoExercicio)entidade;
        try
        {
            String query = "SELECT " + Col_cd_musculo_exercicio;
            for(i = 1; i < colunasBusca.length; i++)
            {
                query += ", " + colunasBusca[i];
            }
            query += " FROM " + nomeTabela + " WHERE 1 = 1";
            if (!TextUtils.isEmpty(musculoExercicio.getID()))
                query += " AND " + Col_cd_musculo_exercicio + " = '" + musculoExercicio.getID() + "'";

            listSql = new ArrayList<EntidadeDominio>();

            listMapSql = new LinkedList<Map<String, String>>(); // talvez seja redundante, testar e tirar se for*****
            listMapSql = db.pesquisarComSelect(query, colunasBusca);
            //db.close();
            for(i = 0; i< listMapSql.size();i++)
            {
                MusculoExercicio me = new MusculoExercicio();
                // ******************* TEM QUE SER A MESMA SEQUENCIA DA LISTA(colunasBusca)***********************
                //{Col_cd_musculo_exercicio,Col_cd_musculo,Col_cd_exercicio,Col_ind_primario_secundario};
                me.setID(listMapSql.get(i).get(colunasBusca[0]));
                me.setIdMusculo(Integer.parseInt(listMapSql.get(i).get(colunasBusca[1])));
                me.setIdExercicio(Integer.parseInt(listMapSql.get(i).get(colunasBusca[2])));
                me.setIndPrimarioSecundario(Integer.parseInt(listMapSql.get(i).get(colunasBusca[3])));

                listSql.add(me);
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
