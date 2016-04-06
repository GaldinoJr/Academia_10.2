package com.example.galdino.academia_102.Telas;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_exercicio_abas);
        // Associa o objeto
        txtNomeGrupo = (TextView)findViewById(R.id.txtNomeGrupo);
        imgCorTelaExer = (ImageView)findViewById(R.id.imgCorTelaExer);
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

        // ABAS
        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Third tab");

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        //tab1.setIndicator("Exercício");

//        View view = LayoutInflater.from(this).inflate(R.layout.activity_tela_exercicio, null);
//        TextView tv = (TextView) view.findViewById(R.id.tabsText);
//        tv.setText(text);
//        ImageView iv = (ImageView) view.findViewById(R.id.tabsImage);
//        iv.setImageResource(image);
//
//        TabHost.TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(view)
//                .setContent(new Intent(this, activity));
//        mTabHost.addTab(setContent);

        //tab1.setIndicator("",getResources().getDrawable(R.drawable.ic_edit_repeticao_amarelo));
        //tab1 = tabHost.newTabSpec("tab_name").setIndicator("Tab Text", getResources().getDrawable(R.drawable.tab_selector)).setContent(intent);
        //tabHost.addTab(spec);


        Intent intentTelaExercicioAba1 = new Intent();
        intentTelaExercicioAba1.setClass(TelaExercicio.this, TelaExercicioAba1.class);
        intentTelaExercicioAba1.putExtra("grupo", grupo);
        intentTelaExercicioAba1.putExtra("nome", nome);
        intentTelaExercicioAba1.putExtra("exe", exe);
        //tab1.setContent(intentTelaExercicioAba1);

        tabHost.addTab(
                tabHost.newTabSpec("tab1")
                        .setIndicator(createTabIndicator(LayoutInflater.from(this), tabHost, R.string.aba1, R.drawable.ic_halter_aba_amarelo))
                        .setContent(intentTelaExercicioAba1)
        );

        //tab2.setIndicator("Músculo");
        Intent intentTelaExercicioAba2 = new Intent();
        intentTelaExercicioAba2.setClass(TelaExercicio.this, TelaExercicioAba2.class);
        intentTelaExercicioAba2.putExtra("grupo", grupo);
        intentTelaExercicioAba2.putExtra("nome", nome);
        //tab2.setContent(intentTelaExercicioAba2);
        tabHost.addTab(
                tabHost.newTabSpec("tab2")
                        .setIndicator(createTabIndicator(LayoutInflater.from(this), tabHost, R.string.aba2, R.drawable.ic_aba_musculo2))
                        .setContent(intentTelaExercicioAba2)
        );

//        tab3.setIndicator("Vídeo");
//        tab3.setContent(new Intent(this,TelaExercicioAba3.class));
        tabHost.addTab(
                tabHost.newTabSpec("tab3")
                        .setIndicator(createTabIndicator(LayoutInflater.from(this), tabHost, R.string.aba3, R.drawable.ic_aba_video))
                        .setContent(new Intent(this,TelaExercicioAba3.class))
        );

        /** Add the tabs  to the TabHost to display. */
       // tabHost.addTab(tab1);
        //tabHost.addTab(tab2);
        //tabHost.addTab(tab3);

    }

    public View createTabIndicator(LayoutInflater inflater, TabHost tabHost, int textResource, int iconResource) {
        View tabIndicator = inflater.inflate(R.layout.xml_exercicio_aba, tabHost.getTabWidget(), false);
        ((TextView) tabIndicator.findViewById(android.R.id.title)).setText(textResource);
        ((ImageView) tabIndicator.findViewById(android.R.id.icon)).setImageResource(iconResource);
        return tabIndicator;
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
