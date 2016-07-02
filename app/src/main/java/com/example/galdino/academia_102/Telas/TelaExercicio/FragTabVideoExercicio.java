package com.example.galdino.academia_102.Telas.TelaExercicio;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.galdino.academia_102.Core.Impl.Controle.Session;
import com.example.galdino.academia_102.Dominio.Documento;
import com.example.galdino.academia_102.R;

/**
 * Created by Galdino on 14/05/2016.
 */
public class FragTabVideoExercicio extends Fragment implements View.OnClickListener {
    // Tela
    private WebView wvVideoExercicio;
    private TextView txtNomeExe,
            txtDescricao;
    private ImageButton btnFullScreen;
    private ProgressBar progress;
    // Variáveis
    private String
            nmExercicio,
            descricao;
    private View v;
    private Session session;
    private String nmGrupo,
                    URL;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        session = Session.getInstance();
        if(!session.isFgPlayVideo())
        {
            v = inflater.inflate(R.layout.tab_exercicio_3_video, container, false);
            wvVideoExercicio = (WebView) v.findViewById(R.id.wvVideoExercicio);
            txtNomeExe = (TextView) v.findViewById(R.id.txtDescriExe);
            txtDescricao = (TextView) v.findViewById(R.id.txtDescricao);
            progress=(ProgressBar) v.findViewById(R.id.pgCarregarVideo);
            btnFullScreen = (ImageButton)v.findViewById(R.id.btnFullScreen);
            btnFullScreen.setOnClickListener(this);
            // TRAVAR
            //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            carregarDados();
            session.setView(v);
        }
        Intent dados = getActivity().getIntent();
        nmGrupo = dados.getStringExtra("nmGrupo");
        //
// Video no youtube
//        wvVideoExercicio.setWebViewClient(new WebViewClient());
//        wvVideoExercicio.getSettings().setJavaScriptEnabled(true);
//        wvVideoExercicio.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        wvVideoExercicio.getSettings().setPluginState(WebSettings.PluginState.ON);
//        wvVideoExercicio.setWebChromeClient(new WebChromeClient());
//        wvVideoExercicio.loadUrl("https://www.youtube.com/watch?v=6qSwM1xM5xc");
        return  session.getView();
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
                if(session.isFgPlayVideo())
                {
                    onResume();
                }
                else
                {
                    abrirVideo();
                }
            }
        }
    }

    @Override
    public void onResume()
    {
        v = session.getView();
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
        session.setFgPlayVideo(true);
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
        wvVideoExercicio.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progress.setVisibility(View.VISIBLE);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                progress.setVisibility(View.GONE);
                // depois que carregar a abertura do vídeo
                //super.onPageFinished(view, url);
                //abrirVideo();
            }
        });
        WebSettings webSettings = wvVideoExercicio.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        wvVideoExercicio.setWebChromeClient(new WebChromeClient() {
        });
        wvVideoExercicio.loadData(frameVideo, "text/html", "utf-8");
        URL = frameVideo;
    }

    @Override
    public void onClick(View view)
    {
        if(view == btnFullScreen)
        {
            Intent intent = new Intent();
            intent.putExtra("url", URL);
            intent.putExtra("nmGrupo", nmGrupo);
            intent.setClass(getContext(), FragTabVideoFullScreen.class);
            session.setView(v);
            startActivity(intent);
        }
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState )
//    {
//        super.onSaveInstanceState(outState);
//        session.setBundleVideo(outState);
//        wvVideoExercicio.saveState(outState);
//
//    }
//
//    @Override
//    public void onViewStateRestored(Bundle savedInstanceState)
//    {
//        super.onViewStateRestored(savedInstanceState);
//        wvVideoExercicio.restoreState(savedInstanceState);
//    }
//    @Override
//    public void onConfigurationChanged(Configuration newConfig)
//    {
//        super.onConfigurationChanged(newConfig);
//
//        // Checks the orientation of the screen
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
//        {
//            fgSegundaVez = true;
//        }
//        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
//        {
//            //Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
//        }
//    }
}