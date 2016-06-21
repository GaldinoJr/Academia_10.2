package com.example.galdino.academia_102.BaseAdapter;

import android.content.Context;

import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.GrupoMuscular;
import com.example.galdino.academia_102.Dominio.Treino;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Galdino on 20/06/2016.
 */
public class ListaTreinosBaseAdapterCLS
{
    private Context context;
    private ArrayList<Treino> alTreinos;
    private List<EntidadeDominio> listEntDomTreinos;
    private boolean fgTelaMeusTreinos;
    private List<GrupoMuscular> lGrupoMuscular;

    // Construtor

    public ListaTreinosBaseAdapterCLS() {
        fgTelaMeusTreinos = false;
        lGrupoMuscular = new ArrayList<GrupoMuscular>();
    }

    // Gets
    public Context getContext() {
        return context;
    }
    public ArrayList<Treino> getAlTreinos() {
        return alTreinos;
    }
    public List<EntidadeDominio> getListEntDomTreinos() {
        return listEntDomTreinos;
    }
    public boolean isFgTelaMeusTreinos() {
        return fgTelaMeusTreinos;
    }

    public List<GrupoMuscular> getlGrupoMuscular() {
        return lGrupoMuscular;
    }

    // Sets
    public void setContext(Context context) {
        this.context = context;
    }
    public void setAlTreinos(ArrayList<Treino> alTreinos) {
        this.alTreinos = alTreinos;
    }
    public void setListEntDomTreinos(List<EntidadeDominio> listEntDomTreinos) {
        this.listEntDomTreinos = listEntDomTreinos;
    }
    public void setFgTelaMeusTreinos(boolean fgTelaMeusTreinos) {
        this.fgTelaMeusTreinos = fgTelaMeusTreinos;
        if(fgTelaMeusTreinos)
        {
            GrupoMuscular grupoMuscular = new GrupoMuscular();
            List<EntidadeDominio> listEntDom = grupoMuscular.operar(context,true, Controler.DF_CONSULTAR, grupoMuscular);
            if(listEntDom != null)
            {
                for(EntidadeDominio ent : listEntDom)
                {
                    lGrupoMuscular.add((GrupoMuscular)ent);
                }
            }
        }
    }
}
