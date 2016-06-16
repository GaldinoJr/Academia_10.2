package com.example.galdino.academia_102.Telas.TelaTreinoPorGrupo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.Abas.ClsTabTreinoPorGrupo;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.Telas.TelaTreinoGrupo;

/**
 * Created by Galdino on 15/05/2016.
 */
public class FragTab1Descricao extends Fragment implements View.OnClickListener {
    // Itens da tela
    private Button btnUtilizarTreino;
    private ImageView imgTreino;
    private TextView txtObjTreino,
            txtNivelTreino,
            txtDescricaoTreino;
    private LinearLayout llFoto;
    // Variaveis
    private Treino treino;
    private ClsTabTreinoPorGrupo tabTreinoPorGrupo = ClsTabTreinoPorGrupo.getInstance();
    private String nomeGrupo;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_treino_1_descricao, container, false);
        //
        imgTreino = (ImageView)v.findViewById(R.id.imgTreino);
        txtObjTreino = (TextView)v.findViewById(R.id.txtObjTreino);
        txtNivelTreino = (TextView)v.findViewById(R.id.txtNivelTreino);
        txtDescricaoTreino = (TextView)v.findViewById(R.id.txtDescricaoTreino);
        llFoto = (LinearLayout)v.findViewById(R.id.llFoto);
        btnUtilizarTreino = (Button)v.findViewById(R.id.btnUtilizarTreino);
        btnUtilizarTreino.setOnClickListener(this);
        treino = tabTreinoPorGrupo.getTreino();
        Intent dados = getActivity().getIntent();
        nomeGrupo = dados.getStringExtra("nmGrupo");
        carregarInformacoesNaTela();
        return v;
    }
    private void carregarInformacoesNaTela()
    {
        int indiceFoto = -1;
        if(treino.getDsNomeFoto() != null && (!treino.getDsNomeFoto().equals("null")))
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

    @Override
    public void onClick(View view)
    {
        if(view == btnUtilizarTreino)
        {
            treino.setFgTreinando(1);
            treino.operar(getContext(),true, Controler.DF_ALTERAR,treino);
            onBackPressed();
        }
    }
    public void onBackPressed() // voltar?
    {
        Intent intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
        intent.setClass(getActivity(), TelaTreinoGrupo.class);
        intent.putExtra("grupo", nomeGrupo);
        intent.putExtra("idTreino", treino.getID());
        startActivity(intent); // chama a próxima tela(tela anterior)
        getActivity().finish();
    }
}