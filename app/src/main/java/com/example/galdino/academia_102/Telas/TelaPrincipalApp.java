package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.Configuracao;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.IniciarBancoExercicios2;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.R.id;

import java.util.List;

public class TelaPrincipalApp extends AppCompatActivity implements View.OnClickListener {

    private Button btnTelaExe,
            btnTelaFicha,
            btnExemploMenu,
            btnExemploMenu2;
    private List<EntidadeDominio> listEntDom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal_app);
        //
        btnTelaExe = (Button)findViewById(id.btnTelaExe);
        btnTelaFicha = (Button)findViewById(id.btnTelaFicha);
        btnExemploMenu = (Button)findViewById(id.btnExemploMenu);
        btnExemploMenu2 = (Button)findViewById(id.btnExemploMenu2);
        //
        btnTelaExe.setOnClickListener(this);
        btnTelaFicha.setOnClickListener(this);
        btnExemploMenu.setOnClickListener(this);
        btnExemploMenu2.setOnClickListener(this);
        // Verifica se é a primeira execução do App no celular e cria o Banco de exercícios
        Configuracao configuracao = new Configuracao();

        listEntDom = configuracao.operar(this, true, Controler.DF_CONSULTAR, configuracao);
        if(listEntDom != null) {
            configuracao = (Configuracao) listEntDom.get(0);
            if(configuracao.getFgBancoCriado() != 1)
            {
                IniciarBancoExercicios2 inicarBancoExercicios = new IniciarBancoExercicios2();
                inicarBancoExercicios.criar(this);
            }
        }
        else
        {
            IniciarBancoExercicios2 inicarBancoExercicios = new IniciarBancoExercicios2();
            inicarBancoExercicios.criar(this);
        }
    }

  //  @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//       // getMenuInflater().inflate(R.menu.tela_principal02, menu);
//        return true;
//    }

    @Override
    public void onClick(View view) {
        if(view == btnTelaExe)
        { // chama a tela principal de exercicios
            Intent intent = new Intent();
            intent.setClass(TelaPrincipalApp.this, TelaPrincipalExercicio.class);
            intent.putExtra("exe", "");
            startActivity(intent); // chama a próxima tela
            finish(); // Encerra a tela atual
        }
        if(view == btnTelaFicha)
        { // chama a tela principal de fichas
            Intent intent = new Intent();
            intent.setClass(TelaPrincipalApp.this, TelaPrincipalTreino.class);
            startActivity(intent); // chama a próxima tela
            finish(); // Encerra a tela atual
        }
        if(view == btnExemploMenu)
        {
            Intent intent = new Intent();
            intent.setClass(TelaPrincipalApp.this, TelaListExercicioNova.class);
            startActivity(intent); // chama a próxima tela
            finish(); // Encerra a tela atual
        }
        if(view == btnExemploMenu2)
        {
            Intent intent = new Intent();
            intent.setClass(TelaPrincipalApp.this, TelaSideMenu.class);
            startActivity(intent); // chama a próxima tela
            finish(); // Encerra a tela atual
        }
    }

}
