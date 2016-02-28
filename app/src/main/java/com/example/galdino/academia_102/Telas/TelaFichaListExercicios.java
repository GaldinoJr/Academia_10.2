package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Exercicio;
import com.example.galdino.academia_102.Dominio.GrupoMuscular;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.R.id;

import java.util.List;

public class TelaFichaListExercicios extends AppCompatActivity implements View.OnClickListener {
    private Button btnAddEx;
    private String nmTreino;
    private TextView lblNmTreino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_ficha_list_exercicios);
        // associa os objetos da tela
        btnAddEx = (Button)findViewById(id.btnAddExercicio);
        lblNmTreino = (TextView)findViewById(id.lblNmTreino);
        // dizer que os botões podem ser clicados
        btnAddEx.setOnClickListener(this);
        //
        // cria a intenção que vai receber os dados da tela 1
        Intent dados = getIntent();
        // Recebe os dados da tela anterior
        nmTreino = dados.getStringExtra("nomeTreino");
        //
        lblNmTreino.setText(nmTreino);
        // Teste Banco de daddos
        List<EntidadeDominio> listEntDom;
        Exercicio exercicio = new Exercicio();
        listEntDom = exercicio.operar(this, true, Controler.DF_CONSULTAR,exercicio);
        if(listEntDom != null) {
            exercicio = (Exercicio) listEntDom.get(0);

            GrupoMuscular grupoMuscular = new GrupoMuscular();
            grupoMuscular.setID(String.valueOf(exercicio.getIdGrupo()));
            listEntDom = grupoMuscular.operar(this, true, Controler.DF_CONSULTAR, grupoMuscular);
            if(listEntDom != null) {
                grupoMuscular = (GrupoMuscular) listEntDom.get(0);
                Toast.makeText(this, "Exercicio: " + exercicio.getNome() + " ID: " + exercicio.getID() +
                        " Grupo: " + grupoMuscular.getNome() + " ID: " + grupoMuscular.getID(), Toast.LENGTH_LONG);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.tela_ficha_list_exercicios, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view == btnAddEx)
        {
            Toast.makeText(TelaFichaListExercicios.this, "clicou", Toast.LENGTH_SHORT).show();
        }

    }
    public void onBackPressed() // voltar?
    {

        Intent intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
        intent.setClass(TelaFichaListExercicios.this, TelaPrincipalFicha.class);
        startActivity(intent); // chama a próxima tela(tela anterior)
        finish();

    }


}
