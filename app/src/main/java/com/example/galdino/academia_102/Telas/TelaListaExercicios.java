package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.example.galdino.academia_102.Dominio.TreinoExercicio;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.R.id;
import com.example.galdino.academia_102.Telas.TelaExercicio.TabPrincipalExercicio;
import com.example.galdino.academia_102.Telas.TelaTreinoPorGrupo.FragTab2Exercicios;
import com.example.galdino.academia_102.Telas.TelaTreinoPorGrupo.TabPrincipalTreinoPorGrupo;

import java.util.ArrayList;
import java.util.List;

public class TelaListaExercicios extends AppCompatActivity {

    private TextView txtQtdSelecionada;
    private int qtdExerciciosSelecionados = 0;
    //private String[] vetExe;
    private ArrayList<String> vetIDExe;
    private String grupo;
    //private ImageView imgCorGrupo;
    private Intent dados;
    private String telaAnterior,
                    nmTreino,
                    idTreino;
    private List<EntidadeDominio> listEntDom;
    private List<EntidadeDominio> listEntDomExercicio;
    private GrupoMuscular grupoMuscular;
    private Exercicio exercicio;
    private static boolean[] itemChecked;
    private static Integer[] vetOrdemExercicio;
    private FloatingActionButton fBtnConfirmarExercicio;
    private int indTela;
    private String fgFocusTabExercicio = "0";
    private ExercicioBaseAdapter exercicioBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_exercicios);
        //
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView txtTituloToolbarPadrao = (TextView) findViewById(R.id.txtTituloToolbarPadrao);
        // FLOAT BUTTON
        fBtnConfirmarExercicio = (FloatingActionButton) findViewById(R.id.fBtnConfirmarExercicio);
        fBtnConfirmarExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(salvarExercíciosTreino() == -1)
                    Toast.makeText(TelaListaExercicios.this, "Erro ao gravar o treino.", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(TelaListaExercicios.this, "Exercícios registrados.", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });
        // associa os objetos
        //imgCorGrupo = (ImageView)findViewById(id.imgPrincipal);
        txtQtdSelecionada = (TextView)findViewById(id.txtQtdSelecionada);
        // recebe os dados da tela 1
        dados = getIntent();
        indTela = 1;
        // Recebe os dados da tela anterior
        fgFocusTabExercicio = dados.getStringExtra("fgFocusTabExercicio");
        grupo = dados.getStringExtra("grupo");
        telaAnterior = dados.getStringExtra("nmTelaCorrespondente");
        if(FragTab2Exercicios.class.toString().equals(telaAnterior)) {
            indTela = 2;
            idTreino = dados.getStringExtra("idTreino");
            nmTreino = dados.getStringExtra("nmTreino");
            vetIDExe = dados.getStringArrayListExtra("exe");
            if(vetIDExe == null)
                vetIDExe = new ArrayList<>();
            fBtnConfirmarExercicio.setVisibility(View.VISIBLE);
        }
        // Carregar o id do grupo no banco
        grupoMuscular = new GrupoMuscular();
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

        //arredonda a imagem
//        RoundAdapter ra = new RoundAdapter();
//        imgCorGrupo.setImageDrawable(ra.RoundImageGrupo(grupo,this));

        // Devolve os conteudos
        txtTituloToolbarPadrao.setText(grupo);
        //
        ArrayList<Exercicio> alExercicio = GetSearchResults();
        final ListView lvExercicio = (ListView)findViewById(id.lvExercicios);
        exercicioBaseAdapter = new ExercicioBaseAdapter(this, alExercicio, itemChecked, indTela, vetIDExe, null, null, vetOrdemExercicio);

        lvExercicio.setAdapter(exercicioBaseAdapter);
        lvExercicio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                String nome = "",
                        exe = "";

                Object o = lvExercicio.getItemAtPosition(position);

                Exercicio obj_itemDetails = (Exercicio) o;
                exe = obj_itemDetails.getNome();
                nome = encontrarNome(exe);
                Intent intent = new Intent();

                // Para chamar a próxima tela tem que dizer qual e a tela atual, e depois a próxima tela( a que vai ser chamada)
                intent.setClass(TelaListaExercicios.this, TabPrincipalExercicio.class);
                intent.putExtra("nmGrupo", grupo);
                intent.putExtra("nmGifExercicio", nome);
                intent.putExtra("nmExercicio", exe);
                startActivity(intent); // chama a próxima tela
                //finish(); // Não faz para não perder as info nem precisar carregar de novo.
            }
        });

        if(FragTab2Exercicios.class.toString().equals(telaAnterior))
            atualizarQtdExerciciosSelecionados("=");
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

    private ArrayList<Exercicio> GetSearchResults()
    {
        int i, qtdRegistro;
        ArrayList<Exercicio> results = new ArrayList<Exercicio>();
        qtdRegistro = listEntDomExercicio.size();
        itemChecked = new boolean[qtdRegistro];
        vetOrdemExercicio = new Integer[qtdRegistro];

        TreinoExercicio treinoExercicio = new TreinoExercicio();
        treinoExercicio.setIdTreino(Integer.parseInt(idTreino));
        List<EntidadeDominio> listAux = treinoExercicio.operar(this, true, Controler.DF_CONSULTAR, treinoExercicio);

        for(i = 0; i < qtdRegistro; i++)
        {
            Exercicio e = (Exercicio)listEntDomExercicio.get(i);
            e.setIdImage(i + 1);
            results.add(e);

            if(listAux != null) {
                for(EntidadeDominio ent : listAux)
                {
                    // Verifica os exercícios do treino corrente e os carrega.
                    TreinoExercicio te = (TreinoExercicio)ent;
                    if(te.getIdExercicio().toString().equals(e.getID()))
                    {
                        itemChecked[i] = true;
                        vetOrdemExercicio[i] = te.getNrOrdem();
                    }
                }
            }
        }// for
        return results;
    }

    private void caregarExercicios()
    {
        TreinoExercicio treinoExercicio = new TreinoExercicio();
        treinoExercicio.setIdTreino(Integer.parseInt(idTreino));
        List<EntidadeDominio> listAux = treinoExercicio.operar(this, true, Controler.DF_CONSULTAR, treinoExercicio);

    }

    private String retornarInfoExercicioNaList2(int linha, int coluna)
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
        if (chk.isChecked())
        {
            itemChecked[linha] = true;
            atualizarOrdemDosExercicios(linha, "+");
        }
        else
        {
            itemChecked[linha] = false;
            atualizarOrdemDosExercicios(linha,"-");
        }
    }

    private void atualizarOrdemDosExercicios(int linha, String operacao)
    {
        atualizarQtdExerciciosSelecionados(operacao);
        int tamanho = vetOrdemExercicio.length;
        Integer nrOrdemCorrente = vetOrdemExercicio[linha];
        if(operacao.equals("+"))
        {
            vetOrdemExercicio[linha] = qtdExerciciosSelecionados;
        }
        else if(operacao.equals("-"))
        {
            if(nrOrdemCorrente != null && nrOrdemCorrente != 0)
            {
                for (int i = 0; i < tamanho; i++)
                {
                    if(vetOrdemExercicio[i] != null && vetOrdemExercicio[i] > vetOrdemExercicio[linha])
                    {
                        vetOrdemExercicio[i] = vetOrdemExercicio[i] - 1;
                    }
                }
            }
            vetOrdemExercicio[linha] = null;
            String id = retornarInfoExercicioNaList2(linha, 0);
            if(id != null)
            {
                int i = 0;
                for (String idExe : vetIDExe) {
                    if (id.equals(idExe)) {
                        vetIDExe.remove(i);
                        break;
                    }
                    i++;
                }
                exercicioBaseAdapter.setVetIDExe(vetIDExe);
                exercicioBaseAdapter.setSelecionados(itemChecked);
            }
        }
        //********
        // Para notificar o base adapter que houve alteração na lista, e assim recarrega-la sem dar reload
        exercicioBaseAdapter.notifyDataSetChanged();
    }

    private void atualizarQtdExerciciosSelecionados(String operacao)
    {
        if(operacao.equals("="))
            qtdExerciciosSelecionados = vetIDExe.size();
        else if(operacao.equals("-"))
            qtdExerciciosSelecionados --;
        else if(operacao.equals("+"))
            qtdExerciciosSelecionados ++;

        txtQtdSelecionada.setText(String.valueOf(qtdExerciciosSelecionados) + " Selecionado(s)");
    }
    private int salvarExercíciosTreino()
    {
        try {
            int i, qtdRegistro = listEntDomExercicio.size();
            TreinoExercicio treinoExercicio = new TreinoExercicio();
            treinoExercicio.setIdTreino(Integer.parseInt(idTreino));
            treinoExercicio.operar(this, true, Controler.DF_EXCLUIR, treinoExercicio);
            for(i = 0; i < qtdRegistro; i++)
            {
                if(itemChecked[i]) {
                    List<EntidadeDominio> listAux;
                    Exercicio ex = (Exercicio) listEntDomExercicio.get(i);
                    treinoExercicio = new TreinoExercicio();
                    treinoExercicio.setIdTreino(Integer.parseInt(idTreino));
                    treinoExercicio.setIdExercicio(Integer.parseInt(ex.getID()));
                    treinoExercicio.setNrOrdem(vetOrdemExercicio[i]);
                    listAux = treinoExercicio.operar(this, true, Controler.DF_CONSULTAR, treinoExercicio);
                    if(listAux == null)
                    {
                        listAux = treinoExercicio.operar(this, true, Controler.DF_SALVAR, treinoExercicio);
                        treinoExercicio = (TreinoExercicio)listAux.get(0);
                        vetIDExe.add(String.valueOf(treinoExercicio.getIdExercicio()));
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
    public void onBackPressed() {
        Intent intent;
        intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e depois a próxima tela( a que vai ser chamada)
        intent.setClass(TelaListaExercicios.this, TabPrincipalTreinoPorGrupo.class);
        intent.putExtra("nmTelaCorrespondente", telaAnterior);
        intent.putExtra("idTreino", idTreino);
        intent.putExtra("nmTreino", nmTreino);
        intent.putExtra("nmGrupo", grupoMuscular.getNome());
        intent.putExtra("fgFocusTabExercicio", fgFocusTabExercicio);
//        intent.putStringArrayListExtra("exe", (ArrayList<String>) vetIDExe);
//        Bundle b=new Bundle();
//        b.putStringArray("exe", vetIDExe);
//        intent.putExtras(b);
        startActivity(intent); // chama a próxima tela
        finish();
    }
}
