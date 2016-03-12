package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.galdino.academia_102.AndroidItens.RoundAdapter;
import com.example.galdino.academia_102.BaseAdapter.ExercicioBaseAdapter;
import com.example.galdino.academia_102.Dominio.Exercicio;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.R.id;

import java.util.ArrayList;

public class TelaListaExercicios extends AppCompatActivity {

    private TextView txtNomeGrupo;
    private String[] vetExe;
    private String grupo;
    private ImageView imgCorGrupo;
    //private CorGrupos corGrupos;
    //private Integer idCor;
    private Intent dados ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_exercicios);
        // associa os objetos
        txtNomeGrupo = (TextView)findViewById(id.txtNomeGrup);
        imgCorGrupo = (ImageView)findViewById(id.imgPrincipal);
        // recebe os dados da tela 1
        dados = getIntent();

        // Recebe os conteudos
        Bundle b=this.getIntent().getExtras();
        vetExe = b.getStringArray("exe");
        grupo = dados.getStringExtra("grupo");

        // Indentifica qual a imagem correspondente do grupo muscular
        //corGrupos = new CorGrupos();
        //idCor = corGrupos.verificarCorGrupo(grupo);

        //arredonda a imagem
        RoundAdapter ra = new RoundAdapter();
        imgCorGrupo.setImageDrawable(ra.RoundImageGrupo(grupo,this));

        // Devolve os conteudos
        // imgCorGrupo.setImageResource(idCor);
        txtNomeGrupo.setText(grupo);


        ArrayList<Exercicio> image_details2 = GetSearchResults();


        final ListView lvExercicio = (ListView)findViewById(id.lvExercicios);

        lvExercicio.setAdapter(new ExercicioBaseAdapter(this, image_details2, grupo));

        lvExercicio.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id)
            {
                String nome = "",
                        exe = "";
                Bundle b2;

                Object o = lvExercicio.getItemAtPosition(position);

                Exercicio obj_itemDetails = (Exercicio)o;
                exe = obj_itemDetails.getNome();
                nome = encontrarNome(exe);
                Intent intent = new Intent();
                b2=new Bundle();

                // Para chamar a próxima tela tem que dizer qual e a tela atual, e depois a próxima tela( a que vai ser chamada)
                intent.setClass(TelaListaExercicios.this, TelaExercicio.class);

                intent.putExtra("grupo", grupo);
                intent.putExtra("nome",nome);
                intent.putExtra("exe", exe);
                //intent.putExtra("idCor", idCor);
                b2.putStringArray("vetExe", vetExe);
                intent.putExtras(b2);

                startActivity(intent); // chama a próxima tela
                finish();
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       //getMenuInflater().inflate(R.menu.tela_lista_exercicios, menu);
        return true;
    }

    public String encontrarNome(String nome)
    {
        int tamanho,
                i;
        String gifNome = "";
        tamanho = nome.length(); // recebe a quantidade de caracteres da palavra
        for(i = 0; i< tamanho; i++) // Roda toda  string
        {
            if(nome.charAt(i) == ' ') // Achou espaço?
            {
                gifNome += String.valueOf(nome.charAt(i +1)).toUpperCase(); // sim pula ele e muda o próximo caracter para maiusculo concatenando na nova string
                i++; // incrementa o indice
            }
            else
                gifNome += nome.charAt(i); // apenas concatena na string
        }
        gifNome = "gif" + gifNome; // Concatena o nome obtido com gif na frente, a fim de terminar o nome
        return gifNome;
    }

    public void onBackPressed() {
        // TODO Auto-generated method stub
        Intent intent;
        intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e depois a próxima tela( a que vai ser chamada)
        intent.setClass(TelaListaExercicios.this, TelaPrincipalExercicio.class);
        startActivity(intent); // chama a próxima tela
        finish();

    }

    public ArrayList<Exercicio> GetSearchResults()
    {
        int i, qtdRegistro;
        Integer[] vetIndice;

        Exercicio exercicio;
        exercicio = new Exercicio();
        // Ordena em ordem alfabetica os vetores

        ArrayList<Exercicio> results = new ArrayList<Exercicio>();
        qtdRegistro = vetExe.length;

        vetIndice = new Integer[qtdRegistro];
        for(i = 0; i < qtdRegistro; i++)
        {
            vetIndice[i] = i;
            exercicio = new Exercicio();
            exercicio.setNome(vetExe[i]);
            exercicio.setIdImage(i+1);
            results.add(exercicio);
        }// for
        return results;
    }

}
