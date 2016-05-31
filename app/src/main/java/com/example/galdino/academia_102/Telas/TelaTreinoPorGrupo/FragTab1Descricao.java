package com.example.galdino.academia_102.Telas.TelaTreinoPorGrupo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.galdino.academia_102.Dominio.Abas.ClsTabTreinoPorGrupo;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.R;

/**
 * Created by Galdino on 15/05/2016.
 */
public class FragTab1Descricao extends Fragment
{
    private ImageView imgTreino;
    private TextView txtObjTreino,
            txtNivelTreino,
            txtDescricaoTreino;
    private LinearLayout llFoto;
    private Treino treino;
    private ClsTabTreinoPorGrupo tabTreinoPorGrupo = ClsTabTreinoPorGrupo.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab2treino_descricao, container, false);
        //
        imgTreino = (ImageView)v.findViewById(R.id.imgTreino);
        txtObjTreino = (TextView)v.findViewById(R.id.txtObjTreino);
        txtNivelTreino = (TextView)v.findViewById(R.id.txtNivelTreino);
        txtDescricaoTreino = (TextView)v.findViewById(R.id.txtDescricaoTreino);
        llFoto = (LinearLayout)v.findViewById(R.id.llFoto);
        treino = tabTreinoPorGrupo.getTreino();
        carregarInformacoesNaTela();
        return v;
    }
    private void carregarInformacoesNaTela()
    {
        int indiceFoto = -1;
        if(treino.getDsNomeFoto() != null)
            indiceFoto = getContext().getResources().getIdentifier(treino.getDsNomeFoto(), "drawable", getContext().getPackageName());
        if(indiceFoto > 0) // debugar quando der merda para ver como fazer o if
            imgTreino.setImageResource(indiceFoto);
        String dsObjTreino = Treino.getDescricaoObjetivo(treino.getIndTipoTreino());
        String dsNivel = Treino.getDescricaoNivel(treino.getIndNivel());
        String dsTreino = treino.getDescricao();
        //
        if( dsObjTreino != null && (!dsObjTreino.equals("")))
            txtObjTreino.setText(dsObjTreino);
        if( dsNivel != null && (!dsNivel.equals("")))
            txtNivelTreino.setText(dsNivel);
        if( dsTreino != null && (!dsTreino.equals("")))
            txtDescricaoTreino.setText(dsTreino);
    }
}