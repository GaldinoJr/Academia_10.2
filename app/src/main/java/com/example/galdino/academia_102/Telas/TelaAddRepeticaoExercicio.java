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
import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Core.Impl.Controle.Session;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.Dominio.TreinoExercicio;
import com.example.galdino.academia_102.Dominio.TreinoExercicioRepeticao;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.Telas.TelaTreinoPorGrupo.TabPrincipalTreinoPorGrupo;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TelaAddRepeticaoExercicio extends AppCompatActivity implements View.OnClickListener {
    private Button btnMaisRepeticao,
            btnMenosRepeticao;
    private FloatingActionButton fBtnConfirmarAddRepeticaoExercicio;
    private EditText edtSerie;
    //private TextView txtNomeGrupoExercicio;
    private ListView lvRepeticoes;
    private String nmTreino,
                   nmGrupo,
                   nmExercicio,
                   idTreinoExercicio;
    private Integer idLinha,
                    idExercicio,
                    idTreino;
    private Session session;
    private TreinoExercicioRepeticao treinoExercicioRepeticao;
    private ArrayList<String> listaCodigosObj;
    private ArrayList<String> listaCodigosNivel;
    //private ArrayList<String> results;
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
        //txtNomeGrupoExercicio = (TextView)findViewById(R.id.txtNomeGrupoExercicio);
        //
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView txtTituloToolbarPadrao = (TextView) findViewById(R.id.txtTituloToolbarPadrao);
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
        idTreino = dados.getIntExtra("idTreino", 0);
        idLinha = dados.getIntExtra("linha", 0);
        idExercicio = dados.getIntExtra("idExercicio", 0);
        txtTituloToolbarPadrao.setText(nmGrupo + "/" + nmExercicio);
        listaCodigosObj = dados.getStringArrayListExtra("listaCodigosObj");
        listaCodigosNivel = dados.getStringArrayListExtra("listaCodigosNivel");
        //
        carregarIDTreinoExercicio();
        //
        session = Session.getInstance();
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
        if(comando.equals("iniciar")) {
            session.setResults(new ArrayList<String>());
            treinoExercicioRepeticao = new TreinoExercicioRepeticao();
            treinoExercicioRepeticao.setID(idTreinoExercicio);
            List<EntidadeDominio> ledTer = treinoExercicioRepeticao.operar(this,true,Controler.DF_CONSULTAR, treinoExercicioRepeticao);
            if(ledTer != null)
            {
                edtSerie.setText(String.valueOf(ledTer.size()));
                for (EntidadeDominio edT : ledTer) {
                    TreinoExercicioRepeticao ter = (TreinoExercicioRepeticao) edT;
                    session.getResults().add(String.valueOf(ter.getNrRepeticoes()));
                }
            }
            else
                session.getResults().add("");
        }
        else if(comando.equals("+")) {
            session.getResults().add("");
        }
        else if(comando.equals("-"))
        {
            int indice = session.getResults().size();
            if(indice > 0)
                session.getResults().remove(indice - 1);
        }
        BaseAdapterRepeticoes bs = new BaseAdapterRepeticoes(this);
        lvRepeticoes.setAdapter(bs);
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
        int qtd = session.getResults().size();
        try
        {
            if(idTreinoExercicio != null)
            {
                treinoExercicioRepeticao = new TreinoExercicioRepeticao();
                treinoExercicioRepeticao.setID(idTreinoExercicio);
                treinoExercicioRepeticao.operar(this, true, Controler.DF_EXCLUIR, treinoExercicioRepeticao);
                if(qtd>0)
                {
                    for (int i = 0; i < qtd; i++)
                    {
                        if(!session.getResults().get(i).equals(""))
                        {
                            TreinoExercicioRepeticao ter = new TreinoExercicioRepeticao();
                            ter.setID(idTreinoExercicio);
                            ter.setNrRepeticoes(Integer.parseInt(session.getResults().get(i)));
                            ter.operar(this, true, Controler.DF_SALVAR, ter);
                        }
                    }
                }
            }
            return 0;
        }
        catch(Exception e)
        {
            return -1;
        }
    }
    private void carregarIDTreinoExercicio()
    {
        TreinoExercicio te = new TreinoExercicio();
        te.setIdTreino(idTreino);
        te.setIdExercicio(idExercicio);
        List<EntidadeDominio> lte = te.operar(this,true,Controler.DF_CONSULTAR,te);
        if(lte != null) {
            te = (TreinoExercicio) lte.get(0);
            idTreinoExercicio = te.getID();
        }
    }
    public void click_btnMenosRepeticaoList(View v)
    {
        int linha = (Integer) v.getTag();
        session.getResults().set(linha, controleNumberPickerHorizontal("-", session.getResults().get(linha)));
        controleList("nada");
    }
    public void click_btnMaisRepeticaoList(View v)
    {
        int linha = (Integer) v.getTag();
        session.getResults().set(linha, controleNumberPickerHorizontal("+", session.getResults().get(linha)));
        controleList("nada");
    }
    public void onBackPressed() // voltar?
    {
        Intent intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
        //intent.setClass(TelaAddRepeticaoExercicio.this, TelaTreinoExercicio.class);
        intent.setClass(TelaAddRepeticaoExercicio.this, TabPrincipalTreinoPorGrupo.class);
        intent.putExtra("nmTreino", nmTreino);
        intent.putExtra("nmGrupo", nmGrupo);
        intent.putExtra("idTreino", String.valueOf(idTreino));
        intent.putStringArrayListExtra("listaCodigosObj", (ArrayList<String>) listaCodigosObj);
        intent.putStringArrayListExtra("listaCodigosNivel", (ArrayList<String>) listaCodigosNivel);
        startActivity(intent); // chama a próxima tela(tela anterior)
        finish();
    }
}
