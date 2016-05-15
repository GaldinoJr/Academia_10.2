package com.example.galdino.academia_102.Telas.TelaExercicio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.galdino.academia_102.Dominio.Documento;
import com.example.galdino.academia_102.R;

/**
 * Created by Galdino on 14/05/2016.
 */
public class FragTabExeExercicio extends Fragment
{
    private TextView txtNomeExe,
    txtDescricao;
    private String nmGifExercicio,
            nmExercicio,
            CaminhoGif,
            nmGrupo,
            descricao,
            aux;
    private WebView wvExercicio;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.activity_tela_exercicio,container,false);
        // Associa os objetos
        txtNomeExe = (TextView)v.findViewById(R.id.txtDescriExe);
        txtDescricao =(TextView)v.findViewById(R.id.txtDescricao);
        wvExercicio = (WebView)v.findViewById(R.id.wvExercicio);

        // cria a intenção que vai receber os dados da tela 1
        Intent dados = getActivity().getIntent();
        // Recebe os dados da tela anterior
        nmGifExercicio = dados.getStringExtra("nmGifExercicio");
        nmExercicio = dados.getStringExtra("nmExercicio");
        nmGrupo = dados.getStringExtra("nmGrupo");
        // Define o caminho do gif
        CaminhoGif = "file:///android_asset/" + nmGrupo +"/"+ nmGifExercicio + ".gif";

        // Devolve os conteudos
        txtNomeExe.setText(nmExercicio);
        wvExercicio.loadUrl(CaminhoGif);
        //
        Documento documento = new Documento(getContext());

        aux = documento.carregarArquivoTxt(nmGrupo, nmGifExercicio, "Descr");
        descricao = txtDescricao.getText().toString() + " ";
        if(aux != null)
            descricao += aux;
        else
            descricao += "Sem informações";
        //
        txtDescricao.setText(descricao);

        return v;
    }
}
