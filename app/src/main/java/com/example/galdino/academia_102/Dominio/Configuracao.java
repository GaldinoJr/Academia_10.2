package com.example.galdino.academia_102.Dominio;


import com.example.galdino.academia_102.Core.Impl.Controle.Session;

/**
 * Created by Galdino on 02/09/2015.
 */
public class Configuracao extends EntidadeDominio{
    private Session session;
    private int fgBancoCriado;

    // Sets
    public void setFgBancoCriado(int fgBancoCriado) {
        this.fgBancoCriado = fgBancoCriado;
    }

    // Gets

    public int getFgBancoCriado() {
        return fgBancoCriado;
    }
}
