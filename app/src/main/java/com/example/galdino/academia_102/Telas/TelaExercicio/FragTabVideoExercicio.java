package com.example.galdino.academia_102.Telas.TelaExercicio;

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

import com.example.galdino.academia_102.R;

/**
 * Created by Galdino on 14/05/2016.
 */
public class FragTabVideoExercicio extends Fragment implements View.OnClickListener {
    private WebView wvVideoExercicio;
    private FloatingActionButton fBtnPlayPause;
    private static boolean fgPlay;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_exercicio_3_video,container,false);
        wvVideoExercicio = (WebView)v.findViewById(R.id.wvVideoExercicio);
        fBtnPlayPause = (FloatingActionButton)v.findViewById(R.id.fBtnPlayPause);
        fBtnPlayPause.setOnClickListener(this);

        wvVideoExercicio.setWebViewClient(new WebViewClient());
        wvVideoExercicio.getSettings().setJavaScriptEnabled(true);
        wvVideoExercicio.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wvVideoExercicio.getSettings().setPluginState(WebSettings.PluginState.ON);
        wvVideoExercicio.setWebChromeClient(new WebChromeClient());
        wvVideoExercicio.loadUrl("https://www.youtube.com/watch?v=6qSwM1xM5xc");
        fgPlay = true;
        // CENTRALIZA A ANIMAÇÃO
        //wvVideoExercicio.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
//        <iframe width=\"640\" height=\"360\"
//          src=\"https://www.youtube.com/embed/4m72jsC_5Ro\" frameborder=\"0\" allowfullscreen>
//         </iframe>
//        wvVideoExercicio.loadDataWithBaseURL("",
//                "<iframe width=\"640\" height=\"360\"\n" +
//                        "src=\"https://www.youtube.com/watch?v=4m72jsC_5Ro\n" +
//                        "</iframe>", "text/html", "utf-8", "");
        return v;
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

    private void controleVideo()
    {
        int indiceFoto;
        String nmFoto ;
        //holder.itemImage.setImageResource(indiceFoto);
        if(!fgPlay)
        {
            nmFoto = "ic_pause_video_preto";
            onResume();
            //wvVideoExercicio.setPluginState(WebSettings.PluginState.ON);
            wvVideoExercicio.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            wvVideoExercicio.getSettings().setJavaScriptEnabled(true);
            onResume();
        }
        else
        {
            nmFoto = "ic_play_video_google";
            onPause();
        }

        indiceFoto = getContext().getResources().getIdentifier(nmFoto, "drawable", getContext().getPackageName());
        fBtnPlayPause.setImageResource(indiceFoto);
        fgPlay = !fgPlay;
    }

    @Override
    public void onClick(View view)
    {
        if(view == fBtnPlayPause)
        {
            controleVideo();
        }
    }
}