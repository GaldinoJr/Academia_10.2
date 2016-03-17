package com.example.galdino.academia_102.Dominio;

import java.util.List;

/**
 * Created by Galdino on 16/03/2016.
 */
public class RetornarInfoExercicioNaList {
    private List<EntidadeDominio> listEntDom;

    public RetornarInfoExercicioNaList(List<EntidadeDominio> listEntDom) {
        this.listEntDom = listEntDom;
    }

    public String getId(int linha) {
        if(listEntDom != null) {
            Exercicio exe = (Exercicio) listEntDom.get(linha);
            return exe.getID();
        }
        else
            return null;
    }

    public String getNome(int linha) {
        if(listEntDom != null) {
            Exercicio exe = (Exercicio) listEntDom.get(linha);
            return exe.getNome();
        }
        else
            return null;
    }

    public String getNomeLogico(int linha) {
        if(listEntDom != null) {
            Exercicio exe = (Exercicio) listEntDom.get(linha);
            return exe.getNomeLogico();
        }
        else
            return null;
    }

    public Integer getIdGrupo(int linha) {
        if(listEntDom != null) {
            Exercicio exe = (Exercicio) listEntDom.get(linha);
            return exe.getIdGrupo();
        }
        else
            return null;
    }
}
