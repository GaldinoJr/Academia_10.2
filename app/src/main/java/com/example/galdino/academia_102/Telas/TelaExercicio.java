package com.example.galdino.academia_102.Telas;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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
        tab1.setIndicator("Exercício");
        Intent intentTelaExercicioAba1 = new Intent();
        intentTelaExercicioAba1.setClass(TelaExercicio.this, TelaExercicioAba1.class);
        intentTelaExercicioAba1.putExtra("grupo", grupo);
        intentTelaExercicioAba1.putExtra("nome",nome);
        intentTelaExercicioAba1.putExtra("exe", exe);
        tab1.setContent(intentTelaExercicioAba1);

        tab2.setIndicator("Músculo");
        tab2.setContent(new Intent(this,TelaExercicioAba2.class));

        tab3.setIndicator("Vídeo");
        tab3.setContent(new Intent(this,TelaExercicioAba3.class));

        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

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
