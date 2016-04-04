package com.example.galdino.academia_102.Telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.galdino.academia_102.AndroidItens.RoundAdapter;
import com.example.galdino.academia_102.Dominio.Documento;
import com.example.galdino.academia_102.R;

/**
 * Created by Galdino on 03/04/2016.
 */
public class TelaExercicioAba1  extends AppCompatActivity
{
//    @Override
//    public void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//
//        TextView tv=new TextView(this);
//        tv.setTextSize(25);
//        tv.setGravity(Gravity.CENTER_VERTICAL);
//        tv.setText("This Is Tab1 Activity");
//
//        setContentView(tv);
//    }
    private TextView txtNomeExe,
           // txtNomeGrupo,
            txtPrimario,
            txtSecundario,
            txtDescricao;
    private String nome,
            exe,
            CaminhoGif,
            grupo,
            primario,
            secundario,
            descricao,
            aux;
    private WebView wvExercicio;
    //private ImageView imgCorTelaExer;
    //private String[] vetExe;
    //private Integer idCor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_exercicio);
        // Associa o objeto
        txtNomeExe = (TextView)findViewById(R.id.txtDescriExe);
       // txtNomeGrupo = (TextView)findViewById(R.id.txtNomeGrupo);
        txtPrimario =(TextView)findViewById(R.id.txtPrimario2);
        txtSecundario = (TextView)findViewById(R.id.txtSecundario);
        txtDescricao =(TextView)findViewById(R.id.txtDescricao);
        //
        wvExercicio = (WebView)findViewById(R.id.wvExercicio);
       // imgCorTelaExer = (ImageView)findViewById(R.id.imgCorTelaExer);
        // cria a intenção que vai receber os dados da tela 1
        Intent dados = getIntent();
        // Recebe os dados da tela anterior
        nome = dados.getStringExtra("nome");
        exe = dados.getStringExtra("exe");
        //idCor = dados.getIntExtra("idCor", 0);

        // Recebe o conteudo do vetor que vai ser devolvido
//        vetExe = (String[])dados.getSerializableExtra("exercicios");
//        Bundle b=this.getIntent().getExtras();
//        vetExe = b.getStringArray("vetExe");
        //
        grupo = dados.getStringExtra("grupo");
        // Define o caminho do gif
        CaminhoGif = "file:///android_asset/" + grupo +"/"+ nome + ".gif";

        // Devolve os conteudos
        txtNomeExe.setText(exe);
       // txtNomeGrupo.setText(grupo);
        // imgCor.setImageResource(idCor);
        //arredonda a imagem
//        RoundAdapter ra = new RoundAdapter();
//        imgCorTelaExer.setImageDrawable(ra.RoundImageGrupo(grupo, this));
        // Exibe a animação em gif
        wvExercicio.loadUrl(CaminhoGif);
        //
        Documento documento = new Documento(this);
        primario =  documento.carregarArquivoTxt(grupo, nome, "Princ");
        if(primario == null)
            primario = txtPrimario.getText().toString();
        secundario = documento.carregarArquivoTxt(grupo, nome, "Sec");
        if(secundario == null)
            secundario = txtSecundario.getText().toString();
        aux = documento.carregarArquivoTxt(grupo, nome, "Descr");
        descricao = txtDescricao.getText().toString() + " ";
        if(aux != null)
            descricao += aux;
        else
            descricao += "Sem informações";
        //
        txtPrimario.setText(primario);
        txtSecundario.setText(secundario);
        txtDescricao.setText(descricao);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        //getMenuInflater().inflate(R.menu.tela_exercicio, menu);
//        return true;
//    }

//    public void onBackPressed()
//    {
//        finish();
//    }

}
