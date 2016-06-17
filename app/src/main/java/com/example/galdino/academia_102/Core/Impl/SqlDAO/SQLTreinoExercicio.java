package com.example.galdino.academia_102.Core.Impl.SqlDAO;

import android.content.Context;
import android.text.TextUtils;

import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.MusculoExercicio;
import com.example.galdino.academia_102.Dominio.TreinoExercicio;
import com.example.galdino.academia_102.Dominio.TreinoExercicioRepeticao;

import java.util.ArrayList;
import java.util.Arrays;
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
    private static final String Col_nr_ordem = "nr_ordem";

    //private static final String[] colunas = {Col_cd_treino,Col_cd_exercicio};
    private static final String[] colunasBusca = {Col_cd_treino_exercicio,Col_cd_treino,Col_cd_exercicio,Col_nr_ordem};
    //private SQL db;
    private Context context;
    private TreinoExercicio treinoExercicio;
    @Override
    protected void iniciar() {
        DATABASE_NAME = "GuiaAcademiaBD";
        nomeTabela = "tb_treino_exercicio";
        sqlCriarTabela = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " ( " +
                Col_cd_treino_exercicio + " INTEGER PRIMARY KEY, " +
                Col_cd_treino + " INTEGER, "+ //FOREIGN KEY
                Col_cd_exercicio + " INTEGER, "+
                Col_nr_ordem + " INTEGER )";
    }

    public SQLTreinoExercicio(Context context)
    {
        this.context = context;
        iniciar();
        db  = SQL.getInstance(context, DATABASE_NAME );
        criarColunas();
        db.popularInfo(nomeTabela, colunas, sqlCriarTabela);
    }

    @Override
    public void criarColunas()
    {
        colunas = new LinkedList<String>();
        colunas.add(Col_cd_treino);
        colunas.add(Col_cd_exercicio);
        colunas.add(Col_nr_ordem);
    }

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        try {
            treinoExercicio =  (TreinoExercicio)entidade;
            mapSql = new HashMap<String, String>();

            mapSql.put(Col_cd_treino, String.valueOf(treinoExercicio.getIdTreino()));
            mapSql.put(Col_cd_exercicio, String.valueOf(treinoExercicio.getIdExercicio()));
            mapSql.put(Col_nr_ordem, String.valueOf(treinoExercicio.getNrOrdem()));
//            removeCamposVazios();
//            long id = db.addRegistro(mapSql);
            long id = Incluir();
            //db.close();
            treinoExercicio.setID(String.valueOf(id));
            return treinoExercicio;
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
        treinoExercicio = (TreinoExercicio)entidade;
        if(treinoExercicio != null)
        {
            List<EntidadeDominio> ledTe = treinoExercicio.operar(context,true,Controler.DF_CONSULTAR,treinoExercicio);
            if (ledTe != null)
            {
                String[] colunas;
                String query = "";
                ArrayList<String> arrayColunas = new ArrayList<>();

                if (treinoExercicio.getIdTreino() != null) {
                    query += Col_cd_treino + " = ? ";
                    arrayColunas.add(treinoExercicio.getIdTreino().toString());
                }
                if (treinoExercicio.getIdExercicio() != null) {
                    query += " AND " + Col_cd_exercicio + " = ?";
                    arrayColunas.add(treinoExercicio.getIdExercicio().toString());
                }

                colunas = new String[arrayColunas.size()];
                for (int i = 0; i < arrayColunas.size(); i++)
                    colunas[i] = arrayColunas.get(i);

                db.deletarComClausula(query, colunas);
                //db.close();

                // Deleta as repetições do exercício naquele treino.
                for(EntidadeDominio ent : ledTe)
                {
                    TreinoExercicioRepeticao ter = new TreinoExercicioRepeticao();
                    ter.setID(ent.getID());
                    ter.operar(context, true, Controler.DF_EXCLUIR, ter);
                }
            }
        }
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
            if (treinoExercicio.getIdExercicio() != null)
                query += " AND " + Col_cd_exercicio + " = " + treinoExercicio.getIdExercicio();
            if (treinoExercicio.getNrOrdem() != null)
                query += " AND " + Col_nr_ordem + " = " + treinoExercicio.getNrOrdem();
            query += " ORDER BY " + Col_nr_ordem;

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
                if(listMapSql.get(i).get(colunasBusca[3]) != null)
                    te.setNrOrdem(Integer.parseInt(listMapSql.get(i).get(colunasBusca[3])));
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
