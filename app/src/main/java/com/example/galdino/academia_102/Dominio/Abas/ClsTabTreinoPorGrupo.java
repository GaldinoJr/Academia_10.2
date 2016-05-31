package com.example.galdino.academia_102.Dominio.Abas;

import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Treino;

/**
 * Created by Galdino on 30/05/2016.
 */
public class ClsTabTreinoPorGrupo extends EntidadeDominio
{
    private Treino treino;

    private static ClsTabTreinoPorGrupo tabTreinoPorGrupo;
    public static ClsTabTreinoPorGrupo getInstance()
    {
        if(tabTreinoPorGrupo == null)
            tabTreinoPorGrupo = new ClsTabTreinoPorGrupo();
        return tabTreinoPorGrupo;
    }
    // Gets
    public  Treino getTreino()
    {
        return treino;
    }

    // Sets
    public  void setTreino(Treino treino)
    {
        this.treino = treino;
    }
}
