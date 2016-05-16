package com.example.galdino.academia_102.Telas.TelaTreinoPorGrupo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.GrupoMuscular;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.SlidingTab.SlidingTabLayout;
import com.example.galdino.academia_102.SlidingTab.ViewPagerAdapterPadrao;
import com.example.galdino.academia_102.Telas.TelaCorpoTreino;
import com.example.galdino.academia_102.Telas.TelaExercicio.FragTabExeExercicio;
import com.example.galdino.academia_102.Telas.TelaExercicio.FragTabMusculoExercicio;
import com.example.galdino.academia_102.Telas.TelaExercicio.FragTabVideoExercicio;
import com.example.galdino.academia_102.Telas.TelaTreinoExercicio;
import com.example.galdino.academia_102.Telas.TelaTreinoGrupo;

import java.util.ArrayList;
import java.util.List;

public class TabPrincipalTreinoPorGrupo extends AppCompatActivity
{
    // Variáveis do slide tab
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapterPadrao adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Descrição","Exercícios"};
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
    private Treino treino;
    private GrupoMuscular grupoMuscular;
    private String idTreino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_tab_padrao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView txtTituloToolbarPadrao = (TextView)findViewById(R.id.txtTituloToolbarPadrao);

        Intent dados = getIntent();
        // Recebe os dados da tela anterior
        listaCodigosObj = dados.getStringArrayListExtra("listaCodigosObj");
        listaCodigosNivel = dados.getStringArrayListExtra("listaCodigosNivel");
        //
        idTreino = dados.getStringExtra("idTreino");
        if(carregarTreino() == -1)
        {
            Toast.makeText(this, "ERRO: Treino não encontrado.", Toast.LENGTH_LONG).show();
            return;
        }
        if(carregarGrupoMuscular() == -1)
        {
            Toast.makeText(this,"ERRO: Grupo muscular não encontrado.",Toast.LENGTH_LONG).show();
            return;
        }
        //
        txtTituloToolbarPadrao.setText(treino.getNome());
        //
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

    private int carregarTreino()
    {
        List<EntidadeDominio> listEntDom;
        treino = new Treino();
        treino.setID(idTreino);
        listEntDom = treino.operar(this,true, Controler.DF_CONSULTAR,treino);
        if(listEntDom != null)
        {
            treino = (Treino)listEntDom.get(0);
            return 0;
        }
        else
            return -1;
    }
    private int carregarGrupoMuscular()
    {
        List<EntidadeDominio> listEntDom;
        grupoMuscular = new GrupoMuscular();
        grupoMuscular.setID(treino.getIdGrupo().toString());
        listEntDom = grupoMuscular.operar(this,true,Controler.DF_CONSULTAR,grupoMuscular);
        if(listEntDom != null)
        {
            grupoMuscular = (GrupoMuscular)listEntDom.get(0);
            return 0;
        }
        else
            return -1;
    }
    public void onBackPressed() // voltar?
    {
        Intent intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
        intent.setClass(TabPrincipalTreinoPorGrupo.this, TelaTreinoGrupo.class);
        intent.putExtra("grupo", grupoMuscular.getNome());
        intent.putStringArrayListExtra("listaCodigosObj", (ArrayList<String>) listaCodigosObj);
        intent.putStringArrayListExtra("listaCodigosNivel", (ArrayList<String>) listaCodigosNivel);
        startActivity(intent); // chama a próxima tela(tela anterior)
        finish();
    }
}
