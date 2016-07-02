package com.example.galdino.academia_102.Telas.TelaExercicio;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.galdino.academia_102.Core.Impl.Controle.Session;
import com.example.galdino.academia_102.R;

public class FragTabVideoFullScreen extends AppCompatActivity
{
    // Variáveis da tela
    private WebView wvVideoExercicio;
    // Variavéis

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_tab_video_full_screen);
        wvVideoExercicio = (WebView) findViewById(R.id.wvVideoExercicio);
        // TRAVAR
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //
//        WebView webView = (WebView)session.getView().findViewById(R.id.wvVideoExercicio);
//        MutableContextWrapper mMutableContext=new MutableContextWrapper(webView.getContext());
//        wvVideoExercicio=new WebView(mMutableContext);
        Intent dados = getIntent();
        String url = dados.getStringExtra("url");
        abrirVideo(url);
    }

    private String getUrl()
    {
        //?start=110&end=119
        //?start=50
        String frameVideo = "<html>\n"+
                "<body>\n" +
                "<table width=\"100%;\" height=\"100%;\">\n"+
                "   <tr>\n" +
                "            <td align=\"center\" valign=\"center\">\n" +
                "               <iframe width=\"100%;\" height=\"150%;\" src=\"https://www.youtube.com/embed/6qSwM1xM5xc?start=50\" frameborder=\"0\" allowfullscreen></iframe>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>\n";
        return frameVideo;
    }
    private void abrirVideo(String url)
    {
        url = getUrl();
        wvVideoExercicio.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = wvVideoExercicio.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        wvVideoExercicio.setWebChromeClient(new WebChromeClient() {
        });
        wvVideoExercicio.loadData(url, "text/html", "utf-8");
    }

    public void onBackPressed() // voltar?
    {
        finish(); // Encerra a tela atual
    }
}
