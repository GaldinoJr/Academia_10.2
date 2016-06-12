package com.example.galdino.academia_102.Dominio.Filtro;

import com.example.galdino.academia_102.Dominio.Treino;

import java.util.ArrayList;

/**
 * Created by Galdino on 12/06/2016.
 */
public class clsFiltroTreino
{
    private static Treino treino;
    private static clsFiltroTreino filtroTreino;
    private boolean fgFiltrado;

    public static clsFiltroTreino getInstance() {
        if (filtroTreino == null) {
            filtroTreino = new clsFiltroTreino();
            filtroTreino.setFgFiltrado(false);
        }
        return filtroTreino;
    }

    // Sets
    public void setFgFiltrado(boolean fgFiltrado) {
        this.fgFiltrado = fgFiltrado;
    }

    // Gets
    public boolean isFgFiltrado() {
        return fgFiltrado;
    }
    public Treino getTreino()
    {
        if(treino == null) {
            treino = new Treino();
            treino.setIndSexo(3);
            treino.setListaCodigosObjParaBusca(new ArrayList());
            treino.setListaCodigosNivelParaBusca(new ArrayList());
        }
        return treino;

    }
}
