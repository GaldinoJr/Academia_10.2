package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

public class TelaFichaListExercicios extends AppCompatActivity {
    private FloatingActionButton fBtnAddEx;
    private String nmTreino,
                   idTreino;
    private TextView lblNmTreino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_ficha_list_exercicios);
        // FLOAT BUTTON
        fBtnAddEx = (FloatingActionButton) findViewById(R.id.fBtnAddExercicio
        );
        fBtnAddEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(TelaFichaListExercicios.this, "clicou", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(TelaFichaListExercicios.this, TelaPrincipalExercicio.class);
                intent.putExtra("idTreino", idTreino);
                intent.putExtra("nmTreino", nmTreino);
                intent.putExtra("nmTelaCorrespondente",TelaFichaListExercicios.class.toString());
                startActivity(intent); // chama a próxima tela
                finish();
            }
        });
        // associa os objetos da tela
        lblNmTreino = (TextView)findViewById(id.lblNmTreino);
        // cria a intenção que vai receber os dados da tela 1
        Intent dados = getIntent();
        // Recebe os dados da tela anterior
        nmTreino = dados.getStringExtra("nomeTreino");
        idTreino = dados.getStringExtra("idTreino");
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
                        " Grupo: " + grupoMuscular.getNome() + " ID: " + grupoMuscular.getID(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.tela_ficha_list_exercicios, menu);
        return true;
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
