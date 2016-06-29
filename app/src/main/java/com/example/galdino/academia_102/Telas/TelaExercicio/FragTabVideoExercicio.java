package com.example.galdino.academia_102.Telas.TelaExercicio;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.galdino.academia_102.Core.Impl.Controle.Session;
import com.example.galdino.academia_102.Dominio.Documento;
import com.example.galdino.academia_102.R;

/**
 * Created by Galdino on 14/05/2016.
 */
public class FragTabVideoExercicio extends Fragment {
    // Tela
    private WebView wvVideoExercicio;
    private TextView txtNomeExe,
            txtDescricao;
    // Variáveis
    private String
            nmExercicio,
            descricao;
    private View v;
    private boolean fgSegundaVez;
    private Session session;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(!fgSegundaVez) {
            v = inflater.inflate(R.layout.tab_exercicio_3_video, container, false);
            wvVideoExercicio = (WebView) v.findViewById(R.id.wvVideoExercicio);
            txtNomeExe = (TextView) v.findViewById(R.id.txtDescriExe);
            txtDescricao = (TextView) v.findViewById(R.id.txtDescricao);
            // TRAVAR
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            carregarDados();
            if(this.isVisible())
                session.setView(v);
        }
        //
// Video no youtube
//        wvVideoExercicio.setWebViewClient(new WebViewClient());
//        wvVideoExercicio.getSettings().setJavaScriptEnabled(true);
//        wvVideoExercicio.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        wvVideoExercicio.getSettings().setPluginState(WebSettings.PluginState.ON);
//        wvVideoExercicio.setWebChromeClient(new WebChromeClient());
//        wvVideoExercicio.loadUrl("https://www.youtube.com/watch?v=6qSwM1xM5xc");
       //
// Minha tentativa
        // Tentativa do Lucas
//        String frameVideo =
//                "<html>" +
//                    "<body>" +
//                        "<div style=\"width=100%; height=40%; margin-top:20%;\">"+
//                            "<iframe width=\"100%;\" height=\"100%;\" src=\"https://www.youtube.com/embed/6qSwM1xM5xc\" frameborder=\"0\" allowfullscreen></iframe>" +
//                        "</div>" +
//                    "</body>" +
//                "</html>";

//
//        String frameVideo =
//                "<html>" +
//                        "<body>" +
//                        "<iframe width=\"320\" height=\"220\" src=\"https://www.youtube.com/embed/6qSwM1xM5xc\" frameborder=\"0\" allowfullscreen></iframe>" +
//                        "</body>" +
//                        "</html>";

//        wvVideoExercicio.loadUrl("javascript:testEcho('Hello World!')");

            return v;
    }
    private void carregarDados()
    {
        String aux, nmGrupo, nmGifExercicio;
        Intent dados = getActivity().getIntent();
        // Recebe os dados
        nmExercicio = dados.getStringExtra("nmExercicio");
        nmGrupo = dados.getStringExtra("nmGrupo");
        nmGifExercicio = dados.getStringExtra("nmGifExercicio");
        //
        txtNomeExe.setText(nmExercicio);
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
    }

    // PARA PAUSAR O VÍDEO APÓS MUDAR DE TAB
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        // Make sure that we are currently visible
        if (this.isVisible())
        {
            // If we are becoming invisible, then...
            if (!isVisibleToUser)
            {
                onPause();
            }
            else
            {
                session = Session.getInstance();
                if(session.isFgPlayVideo())
                {
                    onResume();
                    v = session.getView();
                }
                else
                {
                    session.setFgPlayVideo(true);
                    abrirVideo();
                    fgSegundaVez = true;
                }
            }
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        wvVideoExercicio.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        wvVideoExercicio.onPause();
    }

    private void abrirVideo()
    {
        String frameVideo = "<html>\n"+
                "<body>\n" +
                "<table width=\"100%;\" height=\"100%;\">\n"+
                "   <tr>\n" +
                "            <td align=\"center\" valign=\"center\">\n" +
                "               <iframe width=\"100%;\" height=\"125%;\" src=\"https://www.youtube.com/embed/6qSwM1xM5xc\" frameborder=\"0\" allowfullscreen></iframe>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>\n";
        wvVideoExercicio.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = wvVideoExercicio.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        wvVideoExercicio.setWebChromeClient(new WebChromeClient() {});
        wvVideoExercicio.loadData(frameVideo, "text/html", "utf-8");
    }
}