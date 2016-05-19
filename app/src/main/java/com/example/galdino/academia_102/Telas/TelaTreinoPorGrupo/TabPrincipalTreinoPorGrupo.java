package com.example.galdino.academia_102.Telas.TelaTreinoPorGrupo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.GrupoMuscular;
import com.example.galdino.academia_102.Dominio.RetornarInfoExercicioNaList;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.SlidingTab.SlidingTabLayout;
import com.example.galdino.academia_102.SlidingTab.ViewPagerAdapterPadrao;
import com.example.galdino.academia_102.Telas.TelaTreinoGrupo;

import java.util.ArrayList;
import java.util.List;

public class TabPrincipalTreinoPorGrupo extends AppCompatActivity {
    // Variáveis do slide tab
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapterPadrao adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Descrição", "Exercícios"};
    private int[] icones = {
            -1,
            -1
    };
    private int nrAbas = 2;
    FragTab1Descricao aba1 = new FragTab1Descricao();
    FragTab2Exercicios aba2 = new FragTab2Exercicios();

    private android.support.v4.app.Fragment[] abas = {
            aba1,
            aba2
    };//
    //
    private ArrayList<String> listaCodigosObj;
    private ArrayList<String> listaCodigosNivel;
    private android.support.v4.app.Fragment teste;
    private String nomeTreino,
                    nomeGrupo,
                    idTreino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_tab_padrao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView txtTituloToolbarPadrao = (TextView) findViewById(R.id.txtTituloToolbarPadrao);

        Intent dados = getIntent();
        // Recebe os dados da tela anterior
        listaCodigosObj = dados.getStringArrayListExtra("listaCodigosObj");
        listaCodigosNivel = dados.getStringArrayListExtra("listaCodigosNivel");
        nomeGrupo = dados.getStringExtra("nmGrupo");
        nomeTreino = dados.getStringExtra("nmTreino");
        idTreino = dados.getStringExtra("idTreino");
        txtTituloToolbarPadrao.setText(nomeTreino);
        //
        adapter = new ViewPagerAdapterPadrao(this, getSupportFragmentManager(), Titles, nrAbas, icones, abas);

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
        //teste = adapter.getItem(1); pega o segundo fragment enviado
    }

    public void BtnExcluirExercicioTreino(View v)
    {
        if(aba2 != null && aba2.isVisible())
        {
            if(aba2 instanceof FragTab2Exercicios)
            {
                ((FragTab2Exercicios)aba2).BtnExcluirExercicioTreino_fragment(v);
            }
        }

    }

    public void BtnAddRepeticaoExercicio(View v)
    {
        if(aba2 != null && aba2.isVisible())
        {
            if(aba2 instanceof FragTab2Exercicios)
            {
                ((FragTab2Exercicios)aba2).BtnAddRepeticaoExercicio_fragment(v);
            }
        }

    }
    public void onBackPressed() // voltar?
    {
        Intent intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
        intent.setClass(TabPrincipalTreinoPorGrupo.this, TelaTreinoGrupo.class);
        intent.putExtra("grupo", nomeGrupo);
        intent.putExtra("idTreino", idTreino);
        intent.putStringArrayListExtra("listaCodigosObj", (ArrayList<String>) listaCodigosObj);
        intent.putStringArrayListExtra("listaCodigosNivel", (ArrayList<String>) listaCodigosNivel);
        startActivity(intent); // chama a próxima tela(tela anterior)
        finish();
    }
}
