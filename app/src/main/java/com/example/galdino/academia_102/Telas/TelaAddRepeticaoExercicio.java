package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.galdino.academia_102.R;

import org.w3c.dom.Text;

public class TelaAddRepeticaoExercicio extends AppCompatActivity implements View.OnClickListener {
    private Button btnMaisRepeticao,
            btnMenosRepeticao;
    private FloatingActionButton fBtnConfirmarAddRepeticaoExercicio;
    private TextView txtSerie;
    private ListView lvRepeticoes;
    private String nmTreino,
                   idTreino;
    private Integer idLinha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_add_repeticao_exercicio);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        //
        btnMaisRepeticao = (Button)findViewById(R.id.btnMaisRepeticao);
        btnMenosRepeticao = (Button)findViewById(R.id.btnMenosRepeticao);
        fBtnConfirmarAddRepeticaoExercicio = (FloatingActionButton)findViewById(R.id.fBtnConfirmarAddRepeticaoExercicio);
        lvRepeticoes = (ListView)findViewById(R.id.lvRepeticoes);
        txtSerie = (TextView)findViewById(R.id.txtSerie);
        //
        btnMaisRepeticao.setOnClickListener(this);
        btnMenosRepeticao.setOnClickListener(this);
        //
        fBtnConfirmarAddRepeticaoExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(salvarRepeticoes() == -1)
                    Toast.makeText(TelaAddRepeticaoExercicio.this, "Erro ao gravar repetições do exercício.", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(TelaAddRepeticaoExercicio.this, "Repetições registradas.", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });
        //
        Intent dados = getIntent();
        nmTreino = dados.getStringExtra("nomeTreino");
        idTreino = dados.getStringExtra("idTreino");
        idLinha = dados.getIntExtra("linha",0);
    }

    @Override
    public void onClick(View view)
    {
        if(view == btnMaisRepeticao)
        {
            controleNumberPickerHorizontal("+");
        }
        else if(view == btnMenosRepeticao)
        {
            controleNumberPickerHorizontal("-");
        }
    }

    private void controleNumberPickerHorizontal(String comando)
    {
        int qtdRepeticoes = 0;
        if(!TextUtils.isEmpty(txtSerie.getText()))
            qtdRepeticoes = Integer.parseInt(txtSerie.getText().toString());
        if(qtdRepeticoes > 0)
            if(comando.equals("-"))
                qtdRepeticoes --;
        if(comando.equals("+"))
            qtdRepeticoes ++;
        txtSerie.setText(String.valueOf(qtdRepeticoes));
    }
    private int salvarRepeticoes()
    {
        return -1;
    }
    public void onBackPressed() // voltar?
    {
        Intent intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
        intent.setClass(TelaAddRepeticaoExercicio.this, TelaTreinoExercicio.class);
        intent.putExtra("nomeTreino", nmTreino);
        intent.putExtra("idTreino", idTreino);
        startActivity(intent); // chama a próxima tela(tela anterior)
        finish();
    }
}
