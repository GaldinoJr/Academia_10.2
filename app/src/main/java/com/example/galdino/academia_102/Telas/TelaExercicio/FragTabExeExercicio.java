package com.example.galdino.academia_102.Telas.TelaExercicio;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
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
        View v =inflater.inflate(R.layout.tab_exercicio_1_exercicio,container,false);
        // Era pra ser automatico
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        // Trava
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        //CaminhoGif = "file:///android_asset/" + nmGrupo +"/"+ nmGifExercicio + ".gif";
        //wvExercicio.loadUrl(CaminhoGif);
        CaminhoGif = "file:///android_asset/" + nmGrupo + "/";
        String nmGif = nmGifExercicio + ".gif";

        // Devolve os conteudos
        txtNomeExe.setText(nmExercicio);
        // CENTRALIZA A ANIMAÇÃO
        wvExercicio.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        wvExercicio.loadDataWithBaseURL(CaminhoGif, "<html>\n" +
                "<body bgcolor=\"white\">\n" +
                "    <table width=\"100%\" height=\"100%\">\n" +
                "        <tr>\n" +
                "            <td align=\"center\" valign=\"center\">\n" +
                "                <img src=\"" + nmGif + "\">\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>", "text/html", "utf-8", "");

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
