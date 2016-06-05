package com.example.galdino.academia_102.Telas.TelaExercicio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.galdino.academia_102.Dominio.Documento;
import com.example.galdino.academia_102.R;

/**
 * Created by Galdino on 14/05/2016.
 */
public class FragTabMusculoExercicio extends Fragment
{
    private TextView txtPrimario,
            txtSecundario;
    private String  primario,
            secundario,
            nome,
            grupo;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_exercicio_2_musculos,container,false);
        //
        txtPrimario =(TextView)v.findViewById(R.id.txtPrimario2);
        txtSecundario = (TextView)v.findViewById(R.id.txtSecundario);
        //
        Intent dados = getActivity().getIntent();
        // Recebe os dados da tela anterior
        nome = dados.getStringExtra("nmGifExercicio");
        grupo = dados.getStringExtra("nmGrupo");
        //
        Documento documento = new Documento(getContext());
        primario =  documento.carregarArquivoTxt(grupo, nome, "Princ");
        if(primario == null)
            primario = txtPrimario.getText().toString();
        secundario = documento.carregarArquivoTxt(grupo, nome, "Sec");
        if(secundario == null)
            secundario = txtSecundario.getText().toString();
        //
        txtPrimario.setText(primario);
        txtSecundario.setText(secundario);
        return v;
    }
}
