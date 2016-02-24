package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.R.id;

public class TelaPrincipalApp extends AppCompatActivity implements View.OnClickListener {

    private Button btnTelaExe,
            btnTelaFicha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal_app);
        //
        btnTelaExe = (Button)findViewById(id.btnTelaExe);
        btnTelaFicha = (Button)findViewById(id.btnTelaFicha);
        //
        btnTelaExe.setOnClickListener(this);
        btnTelaFicha.setOnClickListener(this);
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
            intent.setClass(TelaPrincipalApp.this, TelaPrincipal.class);
            startActivity(intent); // chama a próxima tela
            finish(); // Encerra a tela atual
        }
        if(view == btnTelaFicha)
        { // chama a tela principal de fichas
            Intent intent = new Intent();
            intent.setClass(TelaPrincipalApp.this, TelaPrincipalFicha.class);
            startActivity(intent); // chama a próxima tela
            finish(); // Encerra a tela atual
        }
    }

}
