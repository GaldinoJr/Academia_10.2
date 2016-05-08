package com.example.galdino.academia_102.Telas.TelaExercicio;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.galdino.academia_102.AndroidItens.RoundAdapter;
import com.example.galdino.academia_102.R;

public class TelaExercicio extends TabActivity {

    private TextView txtNomeGrupo;
    private String nome,
            exe,
            grupo;
    private ImageView imgCorTelaExer;
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_exercicio_abas);
        // Associa o objeto
        txtNomeGrupo = (TextView)findViewById(R.id.txtNomeGrupo);
        imgCorTelaExer = (ImageView)findViewById(R.id.imgCorTelaExer);
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
        // cria a intenção que vai receber os dados da tela 1
        Intent dados = getIntent();
        // Recebe os dados da tela anterior
        grupo = dados.getStringExtra("grupo");
        txtNomeGrupo.setText(grupo);

        //arredonda a imagem
        RoundAdapter ra = new RoundAdapter();
        imgCorTelaExer.setImageDrawable(ra.RoundImageGrupo(grupo, this));
        // para mandar na aba 1
        exe = dados.getStringExtra("exe");
        nome = dados.getStringExtra("nome");

        // ABA 1
        Intent intentTelaExercicioAba1 = criarAbas(TelaExercicioAba1.class);
        tabHost.addTab(
                tabHost.newTabSpec("tab1")
                        .setIndicator(createTabIndicator(LayoutInflater.from(this), tabHost, R.string.aba1, R.drawable.ic_halter_aba_sm))
                        .setContent(intentTelaExercicioAba1)
        );
        // ABA 2
        Intent intentTelaExercicioAba2 = criarAbas(TelaExercicioAba2.class);
        tabHost.addTab(
                tabHost.newTabSpec("tab2")
                        .setIndicator(createTabIndicator(LayoutInflater.from(this), tabHost, R.string.aba2, R.drawable.ic_aba_musculo_sm))
                        .setContent(intentTelaExercicioAba2)
        );
        // ABA 3
        tabHost.addTab(
                tabHost.newTabSpec("tab3")
                        .setIndicator(createTabIndicator(LayoutInflater.from(this), tabHost, R.string.aba3, R.drawable.ic_aba_video_sm))
                        .setContent(new Intent(this,TelaExercicioAba3.class))
        );
        mudarCorAba(tabHost);
        // Add o listener para quando trocar a aba mudar a cor da aba selecionada, e as outras descelecionadas
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            public void onTabChanged(String tabId) {
                mudarCorAba(tabHost);
            }});
    }

    private Intent criarAbas(Class classe)
    {
        Intent intentNovaAba = new Intent();
        intentNovaAba.setClass(TelaExercicio.this, classe);
        intentNovaAba.putExtra("grupo", grupo);
        intentNovaAba.putExtra("nome", nome);
        intentNovaAba.putExtra("exe", exe);
        return intentNovaAba;
    }
    // Função: Cria as abas personalizadas com descrição e foto
    private View createTabIndicator(LayoutInflater inflater, TabHost tabHost, int textResource, int iconResource)
    {
        View tabIndicator = inflater.inflate(R.layout.xml_exercicio_aba, tabHost.getTabWidget(), false);
        ((TextView) tabIndicator.findViewById(android.R.id.title)).setText(textResource);
        ((ImageView) tabIndicator.findViewById(android.R.id.icon)).setImageResource(iconResource);
        return tabIndicator;
    }
    // Função: Quando trocar a aba mudar a cor da aba selecionada, e as outras descelecionadas
    private static void mudarCorAba(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
        {
            tabhost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.corActionBarPretoEscuro); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundResource(R.color.corListaPretoClaro); // selected
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.tela_exercicio, menu);
        return true;
    }

    public void onBackPressed()
    {
        finish();
    }

}
