package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.galdino.academia_102.BaseAdapter.BaseAdapterRepeticoes;
import com.example.galdino.academia_102.BaseAdapter.ListaTreinosBaseAdapter;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TelaAddRepeticaoExercicio extends AppCompatActivity implements View.OnClickListener {
    private Button btnMaisRepeticao,
            btnMenosRepeticao;
    private FloatingActionButton fBtnConfirmarAddRepeticaoExercicio;
    private EditText edtSerie;
    private TextView txtNomeGrupoExercicio;
    private ListView lvRepeticoes;
    private String nmTreino,
                   nmGrupo,
                   nmExercicio,
                   idTreino;
    private Integer idLinha;
    private ArrayList<String> results;
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED); // Para não iniciar o teclado quando abrir a tela
//        //getMenuInflater().inflate(R.menu.tela_principal_ficha, menu);
//        return true;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_add_repeticao_exercicio);
        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED); // Para não iniciar o teclado quando abrir a tela
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        //
        btnMaisRepeticao = (Button)findViewById(R.id.btnMaisRepeticao);
        btnMenosRepeticao = (Button)findViewById(R.id.btnMenosRepeticao);
        fBtnConfirmarAddRepeticaoExercicio = (FloatingActionButton)findViewById(R.id.fBtnConfirmarAddRepeticaoExercicio);
        lvRepeticoes = (ListView)findViewById(R.id.lvRepeticoes);
        edtSerie = (EditText)findViewById(R.id.edtSerie);
        txtNomeGrupoExercicio = (TextView)findViewById(R.id.txtNomeGrupoExercicio);
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
        nmGrupo = dados.getStringExtra("nomeGrupo");
        nmExercicio = dados.getStringExtra("nomeExercicio");
        idTreino = dados.getStringExtra("idTreino");
        idLinha = dados.getIntExtra("linha", 0);
        txtNomeGrupoExercicio.setText(nmGrupo + "/" + nmExercicio);
        //
        controleList("iniciar");
        //
        if(edtSerie.getText().toString() == "" || TextUtils.isEmpty(edtSerie.getText().toString()) || edtSerie.getText().toString() == null)
            edtSerie.setText("1");
    }

    @Override
    public void onClick(View view)
    {
        if(view == btnMaisRepeticao)
        {
            edtSerie.setText(controleNumberPickerHorizontal("+", edtSerie.getText().toString()));
            controleList("+");
        }
        else if(view == btnMenosRepeticao)
        {
            edtSerie.setText(controleNumberPickerHorizontal("-", edtSerie.getText().toString()));
            controleList("-");
        }
    }

    private void controleList(String comando)
    {
        boolean novaLinha = false;
        if(comando.equals("iniciar")) {
            results = new ArrayList<String>();
            results.add("0");
        }
        else if(comando.equals("+")) {
            results.add("0");
            novaLinha = true;
        }
        else if(comando.equals("-"))
        {
            int indice = results.size();
            if(indice > 0)
                results.remove(indice-1);
            novaLinha = true;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        ArrayList<String> image_details2 = results;
        lvRepeticoes.setAdapter(new BaseAdapterRepeticoes(this, image_details2, novaLinha));
    }
    private String controleNumberPickerHorizontal(String comando, String numero)
    {
        int qtdRepeticoes = 0;
        if(!TextUtils.isEmpty(numero) && numero != null && !numero.equals(""))
            qtdRepeticoes = Integer.parseInt(numero);
        if(qtdRepeticoes > 0)
            if(comando.equals("-"))
                qtdRepeticoes --;
        if(comando.equals("+"))
            qtdRepeticoes ++;

        return String.valueOf(qtdRepeticoes);
    }
    private int salvarRepeticoes()
    {
        return -1;
    }
    public void click_btnMenosRepeticaoList(View v)
    {
        int linha = (Integer) v.getTag();
        int repeticao = (Integer) v.getId();
        results.set(linha,controleNumberPickerHorizontal("-",String.valueOf(repeticao)));
        controleList("nada");
    }
    public void click_btnMaisRepeticaoList(View v)
    {
        //EditText edtRepeticoes = (EditText) v.findViewById(R.id.edtRepeticoes1);
        int linha = (Integer) v.getTag();
        int repeticao = (Integer) v.getId();
        results.set(linha,controleNumberPickerHorizontal("+",String.valueOf(repeticao)));
        controleList("nada");
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
