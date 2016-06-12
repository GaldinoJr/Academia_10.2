package com.example.galdino.academia_102.Core.Impl.Controle;

import android.content.Context;
import android.content.Intent;

import com.example.galdino.academia_102.Dominio.Treino;

import java.util.ArrayList;


/**
 * Created by Galdino on 29/08/2015.
 */
//http://pt.stackoverflow.com/questions/54588/como-criar-manter-variavel-global-em-java-para-login
public class Session {
    // padrão design pattern: singleton.
    private static Session session;
    private static Context context; //Para dizer na FACHADA se á uma requisição interna ou externa
//  private Configuracao configuracaoSistema;
//  private Intent servico;
    private String nameInstanceClass;
    private  ArrayList<String> results;
    //
    private static Treino treino;
    private boolean fgFiltrado;

    // Construtor privado (suprime o construtor público padrão).
    private Session() {}

    public static Session getInstance() {
        if (session == null) {
            session = new Session();
            session.setFgFiltrado(false);
        }
        return session;
    }

    // gets
    public Context getContext() {
        return context;
    }
    public ArrayList<String> getResults() {
        return results;
    }
    public String getNameInstanceClass() {
        return nameInstanceClass;
    }
    public boolean isFgFiltrado() {
        return fgFiltrado;
    }
    public Treino getTreino()
    {
        if(treino == null) {
            treino = new Treino();
            treino.setIndSexo(3);
            treino.setFgCarga(3);
            treino.setListaCodigosObjParaBusca(new ArrayList());
            treino.setListaCodigosNivelParaBusca(new ArrayList());
        }
        return treino;

    }
//
//    public Intent getServico() {
//        return servico;
//    }
//
//
//    public Configuracao getConfiguracaoSistema() {
//        return configuracaoSistema;
//    }

    // sets
    public void setNameInstanceClass(String nameInstanceClass) {
        this.nameInstanceClass = nameInstanceClass;
    }
    public void setResults(ArrayList<String> results) {
        this.results = results;
    }
    public void setFgFiltrado(boolean fgFiltrado)
    {
        if(!fgFiltrado)
            treino = null; // Libera a memoria do filtro que não é mais utilizado.
        this.fgFiltrado = fgFiltrado;
    }
    public void setContext(Context context) {
        this.context = context;
    }
//    public void setServico(Intent servico) {
//        this.servico = servico;
//    }
//
//
//    public void setConfiguracaoSistema(Configuracao configuracaoSistema) {
//        this.configuracaoSistema = configuracaoSistema;
//    }
}
