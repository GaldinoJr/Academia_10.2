package com.example.galdino.academia_102.Telas.TelaExercicio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.galdino.academia_102.Dominio.Documento;
import com.example.galdino.academia_102.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * Created by Galdino on 14/05/2016.
 */
public class FragTabVideoExercicio extends Fragment implements YouTubePlayer.OnInitializedListener {
    // Tela
    //private WebView wvVideoExercicio;
    private TextView txtNomeExe,
            txtDescricao;
    //private YouTubePlayerView youTubePlayerView;
    private YouTubePlayerSupportFragment youTubePlayerView;

    // Variáveis
    private String
            nmExercicio,
            descricao;
    private View v;
    //private Session session;
    public static final String API_KEY = "AIzaSyCb_Dk2wvS3BoWYWkr26F4EuUH4r0JEXug";

    //http://youtu.be/<VIDEO_ID>
    public static final String VIDEO_ID = "6qSwM1xM5xc";
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            v = inflater.inflate(R.layout.tab_exercicio_3_video, container, false);

            txtNomeExe = (TextView) v.findViewById(R.id.txtDescriExe);
            txtDescricao = (TextView) v.findViewById(R.id.txtDescricao);
            //youTubePlayerView = (YouTubePlayerView) v.findViewById(R.id.youtube_player);
            youTubePlayerView = (YouTubePlayerSupportFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.youtube_player);
            youTubePlayerView.initialize(API_KEY, (YouTubePlayer.OnInitializedListener) getContext());

            // TRAVAR
            //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            carregarDados();

        //
// Video no youtube
//        wvVideoExercicio.setWebViewClient(new WebViewClient());
//        wvVideoExercicio.getSettings().setJavaScriptEnabled(true);
//        wvVideoExercicio.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        wvVideoExercicio.getSettings().setPluginState(WebSettings.PluginState.ON);
//        wvVideoExercicio.setWebChromeClient(new WebChromeClient());
//        wvVideoExercicio.loadUrl("https://www.youtube.com/watch?v=6qSwM1xM5xc");
        //return  session.getView();
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
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser)
//    {
//        super.setUserVisibleHint(isVisibleToUser);
//        // Make sure that we are currently visible
//        if (this.isVisible())
//        {
//            // If we are becoming invisible, then...
//            if (!isVisibleToUser)
//            {
//                onPause();
//            }
//            else
//            {
//                if(session.isFgPlayVideo())
//                {
//                    onResume();
//                }
//                else
//                {
//                    abrirVideo();x    
//                }
//            }
//        }
//    }
//    private void abrirVideo()
//    {
//        session.setFgPlayVideo(true);
//    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(getContext(), "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        /** add listeners to YouTubePlayer instance **/
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        /** Start buffering **/
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {

        @Override
        public void onBuffering(boolean arg0) {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onPlaying() {
        }

        @Override
        public void onSeekTo(int arg0) {
        }

        @Override
        public void onStopped() {
        }

    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }

        @Override
        public void onLoaded(String arg0) {
        }

        @Override
        public void onLoading() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onVideoStarted() {
        }
    };
}
