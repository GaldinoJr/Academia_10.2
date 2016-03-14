package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.galdino.academia_102.AndroidItens.RoundAdapter;
import com.example.galdino.academia_102.BaseAdapter.ExercicioBaseAdapter;
import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Exercicio;
import com.example.galdino.academia_102.Dominio.GrupoMuscular;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.R.id;

import java.util.ArrayList;
import java.util.List;

public class TelaListaExercicios extends AppCompatActivity {

    private TextView txtNomeGrupo;
    //private String[] vetExe;
    private String grupo;
    private ImageView imgCorGrupo;
    private Intent dados;
    private String telaAnterior,
                    nmTreino,
                    idTreino;
    private List<EntidadeDominio> listEntDom;
    private List<EntidadeDominio> listEntDomExercicio;
    private GrupoMuscular grupoMuscular;
    private Exercicio exercicio;
    private final List<String> selecionados = new ArrayList<String>();
    private static boolean[] itemChecked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_exercicios);
        // associa os objetos
        txtNomeGrupo = (TextView)findViewById(id.txtNomeGrup);
        imgCorGrupo = (ImageView)findViewById(id.imgPrincipal);
        // recebe os dados da tela 1
        dados = getIntent();

        // Pega os dados do código (não apagar)
        //Bundle b=this.getIntent().getExtras();
        //vetExe = b.getStringArray("exe");

        // Carregar o id do grupo no banco
        grupoMuscular = new GrupoMuscular();
        grupo = dados.getStringExtra("grupo");
        grupoMuscular.setNome(grupo);
        listEntDom = grupoMuscular.operar(this, true, Controler.DF_CONSULTAR, grupoMuscular);
        int idGrupo;
        if(listEntDom != null) {
            grupoMuscular = (GrupoMuscular)listEntDom.get(0);
            idGrupo = Integer.parseInt(grupoMuscular.getID());
        }
        else
        {
            Toast.makeText(this,"Grupo muscular " + grupo + " não existe na base de dados.",Toast.LENGTH_LONG).show();
            return;
        }
        // Carrega os exercícios do grupo correspondente
        exercicio = new Exercicio();
        exercicio.setIdGrupo(idGrupo);
        listEntDomExercicio = exercicio.operar(this,true,Controler.DF_CONSULTAR,exercicio);
        if(listEntDomExercicio == null) {
            Toast.makeText(this,"Não existe exercícios do grupo muscular " + grupo + " na base de dados.",Toast.LENGTH_LONG).show();
            return;
        }
        telaAnterior = dados.getStringExtra("nmTelaCorrespondente");
        if(telaAnterior != null) {
            idTreino = dados.getStringExtra("idTreino");
            nmTreino = dados.getStringExtra("nmTreino");
        }
        //arredonda a imagem
        RoundAdapter ra = new RoundAdapter();
        imgCorGrupo.setImageDrawable(ra.RoundImageGrupo(grupo,this));

        // Devolve os conteudos
        txtNomeGrupo.setText(grupo);


        ArrayList<Exercicio> image_details2 = GetSearchResults();


        final ListView lvExercicio = (ListView)findViewById(id.lvExercicios);

        lvExercicio.setAdapter(new ExercicioBaseAdapter(this, image_details2, grupo, itemChecked));

        lvExercicio.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id)
            {
                String nome = "",
                        exe = "";
               // Bundle b2;

                Object o = lvExercicio.getItemAtPosition(position);

                Exercicio obj_itemDetails = (Exercicio)o;
                exe = obj_itemDetails.getNome();
                nome = encontrarNome(exe);
                Intent intent = new Intent();
                //b2=new Bundle();

                // Para chamar a próxima tela tem que dizer qual e a tela atual, e depois a próxima tela( a que vai ser chamada)
                intent.setClass(TelaListaExercicios.this, TelaExercicio.class);

                intent.putExtra("grupo", grupo);
                intent.putExtra("nome",nome);
                intent.putExtra("exe", exe);
                //intent.putExtra("idCor", idCor);
                //b2.putStringArray("vetExe", vetExe);
                //intent.putExtras(b2);

                startActivity(intent); // chama a próxima tela
                //finish();
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
        //Integer[] vetIndice;
        ArrayList<Exercicio> results = new ArrayList<Exercicio>();
        //qtdRegistro = vetExe.length;
        qtdRegistro = listEntDomExercicio.size();
        itemChecked = new boolean[qtdRegistro];
        //vetIndice = new Integer[qtdRegistro];
        for(i = 0; i < qtdRegistro; i++)
        {
            //vetIndice[i] = i;
            Exercicio e = new Exercicio();
            e = (Exercicio)listEntDomExercicio.get(i);
            e.setIdImage(i+1);
            results.add(e);
        }// for
        return results;
    }
    public String retornarInfoExercicioNaList(int linha, int coluna)
    {
        if(listEntDomExercicio != null) {
            Exercicio exe = (Exercicio) listEntDomExercicio.get(linha);
            if(coluna == 0)
                return exe.getID();
            else if(coluna == 1)
                return exe.getNome();
        }
        return "Sem informação";
    }
   public void BtnCheckExercicio(View v)
    {
        CheckBox chk = (CheckBox) v.findViewById(R.id.chkExercicioSelecionado);
        int linha = (Integer) v.getTag();
        if (chk.isChecked()) {
            itemChecked[linha] = true;
            Toast.makeText(getApplicationContext(), "Checbox do exercício " + retornarInfoExercicioNaList(linha,1) + " marcado!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Checbox do exercício " + retornarInfoExercicioNaList(linha,1) + " desmarcado!", Toast.LENGTH_SHORT).show();
            itemChecked[linha] = false;
        }
    }
}
