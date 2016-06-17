package com.example.galdino.academia_102.Core.Impl.SqlDAO;

import android.content.Context;
import android.text.TextUtils;

import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.TreinoExercicio;
import com.example.galdino.academia_102.Dominio.TreinoExercicioRepeticao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Galdino on 01/04/2016.
 */
public class SQLTreinoExercicioRepeticao extends AbsSQL {
    private static final String Col_cd_treino_exercicio = "cd_treino_exercicio";
    private static final String Col_nr_repeticoes = "nr_repeticoes";

    //private static final String[] colunas = {Col_cd_treino_exercicio,Col_nr_repeticoes};
    private static final String[] colunasBusca = {Col_cd_treino_exercicio,Col_nr_repeticoes};
    //private SQL db;

    private TreinoExercicioRepeticao treinoExercicioRepeticao;
    @Override
    protected void iniciar() {
        DATABASE_NAME = "GuiaAcademiaBD";
        nomeTabela = "tb_treino_exercicio_repeticao";
        sqlCriarTabela = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " ( " +
                Col_cd_treino_exercicio + " INTEGER, " +
                Col_nr_repeticoes + " INTEGER )";
    }

    public SQLTreinoExercicioRepeticao(Context context)
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
        colunas.add(Col_cd_treino_exercicio);
        colunas.add(Col_nr_repeticoes);
    }

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        try {
            treinoExercicioRepeticao =  (TreinoExercicioRepeticao)entidade;
            mapSql = new HashMap<String, String>();

            mapSql.put(Col_cd_treino_exercicio, String.valueOf(treinoExercicioRepeticao.getID()));
            mapSql.put(Col_nr_repeticoes, String.valueOf(treinoExercicioRepeticao.getNrRepeticoes()));
//            removeCamposVazios();
//            long id = db.addRegistro(mapSql);
            long id = Incluir();
            //db.close();
            treinoExercicioRepeticao.setID(String.valueOf(id));
            return treinoExercicioRepeticao;
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
        treinoExercicioRepeticao = (TreinoExercicioRepeticao)entidade;
        if(treinoExercicioRepeticao != null)
        {
            String[] colunas = new String[1];
            String query = "";
            ArrayList<String> arrayColunas = new ArrayList<>();
            if(treinoExercicioRepeticao.getID() != null) {
                query += Col_cd_treino_exercicio + " = ? ";
                colunas[0] = treinoExercicioRepeticao.getID().toString();
                db.deletarComClausula(query, colunas);
                //db.close();
            }
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade)
    {
        int i;
        treinoExercicioRepeticao = (TreinoExercicioRepeticao)entidade;
        try
        {
            String query = "SELECT " + Col_cd_treino_exercicio;
            for(i = 1; i < colunasBusca.length; i++)
            {
                query += ", " + colunasBusca[i];
            }
            query += " FROM " + nomeTabela + " WHERE 1 = 1";
            if (!TextUtils.isEmpty(treinoExercicioRepeticao.getID()))
                query += " AND " + Col_cd_treino_exercicio + " = " + treinoExercicioRepeticao.getID();
//            if (treinoExercicioRepeticao.getNrRepeticoes() != null)
//                query += " AND " + Col_nr_repeticoes + " = " + treinoExercicioRepeticao.getNrRepeticoes();

            listSql = new ArrayList<EntidadeDominio>();
            listMapSql = new LinkedList<Map<String, String>>(); // talvez seja redundante, testar e tirar se for*****
            listMapSql = db.pesquisarComSelect(query, colunasBusca);
            //db.close();
            for(i = 0; i< listMapSql.size();i++)
            {
                TreinoExercicioRepeticao ter = new TreinoExercicioRepeticao();
                // ******************* TEM QUE SER A MESMA SEQUENCIA DA LISTA(colunasBusca)***********************
                //Col_cd_treino_exercicio,Col_cd_treino,Col_cd_exercicio,Col_nr_repeticoes}
                ter.setID(listMapSql.get(i).get(colunasBusca[0]));
                ter.setNrRepeticoes(Integer.parseInt(listMapSql.get(i).get(colunasBusca[1])));


                listSql.add(ter);
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
