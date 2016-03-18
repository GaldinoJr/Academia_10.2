package com.example.galdino.academia_102.Core.Impl.SqlDAO;

import android.content.Context;
import android.text.TextUtils;

import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.MusculoExercicio;
import com.example.galdino.academia_102.Dominio.TreinoExercicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Galdino on 27/02/2016.
 */
public class SQLTreinoExercicio extends AbsSQL {
    private static final String Col_cd_treino_exercicio = "cd_treino_exercicio";
    private static final String Col_cd_treino = "f_cd_treino";
    private static final String Col_cd_exercicio = "f_cd_exercicio";
    private static final String Col_nr_repeticoes = "nr_repeticoes";
    private static final String[] colunas = {Col_cd_treino,Col_cd_exercicio,Col_nr_repeticoes};
    private static final String[] colunasBusca = {Col_cd_treino_exercicio,Col_cd_treino,Col_cd_exercicio,Col_nr_repeticoes};
    private SQL db;

    private TreinoExercicio treinoExercicio;
    @Override
    protected void iniciar() {
        DATABASE_NAME = "GuiaAcademiaBD";
        nomeTabela = "tb_treino_exercicio";
        sqlCriarTabela = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " ( " +
                Col_cd_treino_exercicio + " INTEGER PRIMARY KEY, " +
                Col_cd_treino + " INTEGER, "+ //FOREIGN KEY
                Col_cd_exercicio + " INTEGER, "+ //FOREIGN KEY ** Não funciona sa desgraça, ja me fodeu TRES vezes
                Col_nr_repeticoes + " INTEGER )";
    }

    public SQLTreinoExercicio(Context context)
    {
        iniciar();
        db  = SQL.getInstance(context, DATABASE_NAME );
        db.popularInfo(nomeTabela, colunas, sqlCriarTabela);
    }

    @Override
    public void salvar(EntidadeDominio entidade) {
        try {
            treinoExercicio =  (TreinoExercicio)entidade;
            mapSql = new HashMap<String, String>();

            mapSql.put(Col_cd_treino, String.valueOf(treinoExercicio.getIdTreino()));
            mapSql.put(Col_cd_exercicio, String.valueOf(treinoExercicio.getIdExercicio()));
            mapSql.put(Col_nr_repeticoes, String.valueOf(treinoExercicio.getNrRepeticoes()));
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
        treinoExercicio = (TreinoExercicio)entidade;
        try
        {
            String query = "SELECT " + Col_cd_treino_exercicio;
            for(i = 1; i < colunasBusca.length; i++)
            {
                query += ", " + colunasBusca[i];
            }
            query += " FROM " + nomeTabela + " WHERE 1 = 1";
            if (!TextUtils.isEmpty(treinoExercicio.getID()))
                query += " AND " + Col_cd_treino_exercicio + " = '" + treinoExercicio.getID() + "'";
            if (treinoExercicio.getIdTreino() != null)
                query += " AND " + Col_cd_treino + " = " + treinoExercicio.getIdTreino();

            listSql = new ArrayList<EntidadeDominio>();

            listMapSql = new LinkedList<Map<String, String>>(); // talvez seja redundante, testar e tirar se for*****
            listMapSql = db.pesquisarComSelect(query, colunasBusca);
            //db.close();
            for(i = 0; i< listMapSql.size();i++)
            {
                TreinoExercicio te = new TreinoExercicio();
                // ******************* TEM QUE SER A MESMA SEQUENCIA DA LISTA(colunasBusca)***********************
                //Col_cd_treino_exercicio,Col_cd_treino,Col_cd_exercicio,Col_nr_repeticoes}
                te.setID(listMapSql.get(i).get(colunasBusca[0]));
                te.setIdTreino(Integer.parseInt(listMapSql.get(i).get(colunasBusca[1])));
                te.setIdExercicio(Integer.parseInt(listMapSql.get(i).get(colunasBusca[2])));
                if(!listMapSql.get(i).get(colunasBusca[3]).equals("null"))
                    te.setNrRepeticoes(Integer.parseInt(listMapSql.get(i).get(colunasBusca[3])));

                listSql.add(te);
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
