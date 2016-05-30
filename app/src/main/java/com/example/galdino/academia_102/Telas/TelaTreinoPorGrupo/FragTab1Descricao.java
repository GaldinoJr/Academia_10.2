package com.example.galdino.academia_102.Telas.TelaTreinoPorGrupo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.R;

import java.util.List;

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
    // idTreino
    private String idTreino;
    private Treino treino;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab2treino_descricao, container, false);
        //
        imgTreino = (ImageView)v.findViewById(R.id.imgTreino);
        txtObjTreino = (TextView)v.findViewById(R.id.txtObjTreino);
        txtNivelTreino = (TextView)v.findViewById(R.id.txtNivelTreino);
        txtDescricaoTreino = (TextView)v.findViewById(R.id.txtDescricaoTreino);
        llFoto = (LinearLayout)v.findViewById(R.id.llFoto);
        // cria a intenção que vai receber os dados da tela 1
        Intent dados = getActivity().getIntent();
        idTreino = dados.getStringExtra("idTreino");
        if(carregarTreino() == -1)
        {
            Toast.makeText(getContext(), "ERRO: Treino não encontrado.", Toast.LENGTH_LONG).show();
            //return;
        }
        carregarInformacoesNaTela();
        return v;
    }
    private void carregarInformacoesNaTela()
    {
        Context context = getActivity().getBaseContext();
        Context context1 =getContext();
        int indiceFoto = context.getResources().getIdentifier(treino.getDsNomeFoto(), "drawable", context.getPackageName());
        if(indiceFoto > 0) // debugar quando der merda para ver como fazer o if
            imgTreino.setImageResource(indiceFoto);
//        Toast.makeText(getContext(), "Indice." + String.valueOf(indiceFoto), Toast.LENGTH_LONG).show();
//        Toast.makeText(getContext(), "tipo." + treino.getIndTipoTreino(), Toast.LENGTH_LONG).show();
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
    private int carregarTreino()
    {
        List<EntidadeDominio> listEntDom;
        treino = new Treino();
        treino.setID(idTreino);
        listEntDom = treino.operar(getContext(), true, Controler.DF_CONSULTAR, treino);
        if(listEntDom != null)
        {
            treino = (Treino)listEntDom.get(0);
            return 0;
        }
        else
            return -1;
    }
}