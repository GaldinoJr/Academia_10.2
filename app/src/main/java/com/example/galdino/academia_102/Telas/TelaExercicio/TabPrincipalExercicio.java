package com.example.galdino.academia_102.Telas.TelaExercicio;

import android.app.Fragment;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.galdino.academia_102.Core.Impl.Controle.Session;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Exercicio;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.SlidingTab.SlidingTabLayout;
import com.example.galdino.academia_102.SlidingTab.ViewPagerAdapterPadrao;
import com.example.galdino.academia_102.Telas.TelaSideMenu;

public class TabPrincipalExercicio  extends ActionBarActivity {

    // Variáveis do slide tab
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapterPadrao adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Exercício","Músculo", "Vídeo"};
    private int[] icones = {
            -1,
            -1
    };
    // Colocar ícones nas tabs
//    private int[] icones = {
//            R.drawable.ic_halter_aba_sm,
//            R.drawable.ic_aba_musculo_sm,
//            R.drawable.ic_aba_video_sm
//    };
    private int nrAbas =3;
    FragTabExeExercicio aba1 = new FragTabExeExercicio();
    FragTabMusculoExercicio aba2 = new FragTabMusculoExercicio();
    FragTabVideoExercicio aba3 = new FragTabVideoExercicio();
    private android.support.v4.app.Fragment[] abas = {
        aba1,
        aba2,
        aba3
    };

    // Variáveis da tela
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_tab_padrao);
        Intent dados = getIntent();
        // para mandar na aba 1

        // Creating The Toolbar and setting it as the Toolbar for the activity
        TextView txtTituloToolbarPadrao = (TextView)findViewById(R.id.txtTituloToolbarPadrao);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("");
        //txtTituloToolbarPadrao.setText(dados.getStringExtra("nmExercicio"));
        txtTituloToolbarPadrao.setText("How to Train");
        setSupportActionBar(toolbar);
        //

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapterPadrao(this,getSupportFragmentManager(),Titles,nrAbas,icones,abas);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
        tabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
        //tabs.setCustomTabView(R.layout.custom_tab_view, 0);
        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.corDetalhesAmarelos);
            }
        });
        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }
    public void onBackPressed() // voltar?
    {
        Session session = Session.getInstance();
        session.setFgPlayVideo(false);
        Intent intent = new Intent();
        intent.setClass(TabPrincipalExercicio.this, TelaSideMenu.class);
        startActivity(intent); // chama a próxima tela
        finish(); // Encerra a tela atual
    }
}
