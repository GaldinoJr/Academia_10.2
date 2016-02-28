package com.example.galdino.academia_102.Core.Impl.Controle;

import android.content.Context;
import android.content.Intent;


/**
 * Created by Galdino on 29/08/2015.
 */
//http://pt.stackoverflow.com/questions/54588/como-criar-manter-variavel-global-em-java-para-login
public class Session {
    // padrão design pattern: singleton.
    private static Session session;
    private static Context context; //Para dizer na FACHADA se á uma requisição interna ou externa
    //    private Residencia residencia;
//    private Configuracao configuracaoSistema;
    private Intent servico;
    private String nameInstanceClass;

    // Construtor privado (suprime o construtor público padrão).
    private Session() {}

    public static Session getInstance() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    // gets
    public Context getContext() {
        return context;
    }


    public String getNameInstanceClass() {
        return nameInstanceClass;
    }
    //
//
//    public Intent getServico() {
//        return servico;
//    }
//
//    public Residencia getResidencia() {
//        return residencia;
//    }
//
//    public Configuracao getConfiguracaoSistema() {
//        return configuracaoSistema;
//    }
//
//
//    // sets
//
    public void setNameInstanceClass(String nameInstanceClass) {
        this.nameInstanceClass = nameInstanceClass;
    }
//
//    public void setServico(Intent servico) {
//        this.servico = servico;
//    }
//
//    public void setResidencia(Residencia residencia) {
//        this.residencia = residencia;
//    }
//
    public void setContext(Context context) {
        this.context = context;
    }
//
//    public void setConfiguracaoSistema(Configuracao configuracaoSistema) {
//        this.configuracaoSistema = configuracaoSistema;
//    }
}
