package com.example.galdino.academia_102.Telas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.galdino.academia_102.BaseAdapter.ExercicioBaseAdapter;
import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Exercicio;
import com.example.galdino.academia_102.Dominio.GrupoMuscular;
import com.example.galdino.academia_102.Dominio.RetornarInfoExercicioNaList;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.Dominio.TreinoExercicio;
import com.example.galdino.academia_102.Dominio.TreinoExercicioRepeticao;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.R.id;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TelaTreinoExercicio extends AppCompatActivity {
    private ListView lvTreinoExercicio;
    private List<EntidadeDominio> listEntDomExercicio;
    private FloatingActionButton fBtnAddEx;
    private String nmTreino,
                   idTreino;
    private TextView lblNmTreino;
    private ArrayList<String> vetIDExe;
    private ArrayList<String> vetRepeticoesExe;
    private ArrayList<Exercicio> results;
    private RetornarInfoExercicioNaList retornarInfoExercicioNaList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_treino_exercicio);
        // associa os objetos da tela
        lblNmTreino = (TextView)findViewById(id.lblNmTreino);
        lvTreinoExercicio = (ListView)findViewById(id.lvTreinoExercicio);
        fBtnAddEx = (FloatingActionButton) findViewById(R.id.fBtnAddExercicio
        // FLOAT BUTTON
        );
        fBtnAddEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(TelaTreinoExercicio.this, "clicou", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(TelaTreinoExercicio.this, TelaPrincipalExercicio.class);
                intent.putExtra("idTreino", idTreino);
                intent.putExtra("nmTreino", nmTreino);
                intent.putExtra("nmTelaCorrespondente",TelaTreinoExercicio.class.toString());
                intent.putStringArrayListExtra("exe", (ArrayList<String>) vetIDExe);
                //Bundle b=new Bundle();
//                b.putStringArray("exe", vetIDExe);
//                intent.putExtras(b);
                startActivity(intent); // chama a próxima tela
                finish();
            }
        });
        // cria a intenção que vai receber os dados da tela 1
        Intent dados = getIntent();
        // Recebe os dados da tela anterior
        nmTreino = dados.getStringExtra("nomeTreino");
        idTreino = dados.getStringExtra("idTreino");
        //
        lblNmTreino.setText(nmTreino);
        // Carregar a lista de exercício com os exercícios do treino correspondente
        atualizarListExercicio();
        // monta a lista
        if(listEntDomExercicio != null) {
            final Context context = this;

            lvTreinoExercicio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    String NomeLogico = "",
                            exe = "";
                    Object o = lvTreinoExercicio.getItemAtPosition(position);

                    Exercicio obj_itemDetails = (Exercicio) o;
                    exe = obj_itemDetails.getNome();
                    //nome = encontrarNome(exe);
                    NomeLogico = retornarInfoExercicioNaList.getNomeLogico(position);
                    // carrega o grupo desse exercicio
                    GrupoMuscular grupoMuscular = new GrupoMuscular();
                    grupoMuscular.setID(String.valueOf(retornarInfoExercicioNaList.getIdGrupo(position)));
                    List<EntidadeDominio> listEntDom = grupoMuscular.operar(context, true, Controler.DF_CONSULTAR, grupoMuscular);
                    String grupo = "";
                    if (listEntDom != null) {
                        grupoMuscular = (GrupoMuscular) listEntDom.get(0);
                        grupo = grupoMuscular.getNome();
                    }
                    Intent intent = new Intent();
                    // Para chamar a próxima tela tem que dizer qual e a tela atual, e depois a próxima tela( a que vai ser chamada)
                    intent.setClass(TelaTreinoExercicio.this, TelaExercicio.class);
                    intent.putExtra("grupo", grupo);
                    intent.putExtra("nome", NomeLogico);
                    intent.putExtra("exe", exe);

                    startActivity(intent); // chama a próxima tela
                    //finish();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.tela_ficha_list_exercicios, menu);
        return true;
    }

    private String carregarRepeticaoExercicio(String idTreinoExercicio)
    {
        TreinoExercicioRepeticao treinoExercicioRepeticao = new TreinoExercicioRepeticao();
        treinoExercicioRepeticao.setID(idTreinoExercicio);
        List<EntidadeDominio> ledTer = treinoExercicioRepeticao.operar(this,true,Controler.DF_CONSULTAR,treinoExercicioRepeticao);
        if(ledTer != null)
        {
            String repeticoes = ledTer.size() + " x ";
            for(EntidadeDominio ent : ledTer)
            {
                TreinoExercicioRepeticao ter = (TreinoExercicioRepeticao)ent;
                repeticoes += ter.getNrRepeticoes() + " - ";
            }
            return repeticoes.substring(0,repeticoes.length()-3);
        }
        else
            return null;
    }
    private void carregarExerciciosTreino()
    {
        List<EntidadeDominio> listEntDomTreinoExercicio;
        TreinoExercicio treinoExercicio = new TreinoExercicio();
        treinoExercicio.setIdTreino(Integer.parseInt(idTreino));
        listEntDomTreinoExercicio = treinoExercicio.operar(this,true,Controler.DF_CONSULTAR,treinoExercicio);
        results = new ArrayList<Exercicio>();
        listEntDomExercicio = new LinkedList<>();
        vetIDExe = new ArrayList<>();
        vetRepeticoesExe = new ArrayList<>();
        if(listEntDomTreinoExercicio != null)
        {

            for (EntidadeDominio entDomTreinoExercico: listEntDomTreinoExercicio)
            {
                TreinoExercicio te = (TreinoExercicio)entDomTreinoExercico;
                Exercicio exercicio = new Exercicio();
                exercicio.setID(String.valueOf(te.getIdExercicio()));
                List<EntidadeDominio> listEntDomExe = exercicio.operar(this,true,Controler.DF_CONSULTAR,exercicio);
                exercicio = (Exercicio)listEntDomExe.get(0);
                results.add(exercicio);
                listEntDomExercicio.add(exercicio);
                vetIDExe.add(exercicio.getID());
                vetRepeticoesExe.add(carregarRepeticaoExercicio(te.getID()));
            }
            retornarInfoExercicioNaList = new RetornarInfoExercicioNaList(listEntDomExercicio);
        }
    }
    public void BtnExcluirExercicioTreino(View v)
    {
        // VERIFICAR SE CONFIRMA A EXCLUSÃO DO EXERCÍCIO
        if(listEntDomExercicio != null)
        {
            try {
                int linha = (Integer) v.getTag();
                TreinoExercicio treinoExercicioExclui = new TreinoExercicio();
                treinoExercicioExclui.setIdTreino(Integer.parseInt(idTreino));
                treinoExercicioExclui.setIdExercicio(Integer.parseInt(retornarInfoExercicioNaList.getId(linha)));
                treinoExercicioExclui.operar(this, true, Controler.DF_EXCLUIR, treinoExercicioExclui);
                atualizarListExercicio();
                Toast.makeText(this,"Exercício removido do treino.",Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                Toast.makeText(this,"Erro ao excluir o exercício.",Toast.LENGTH_LONG).show();
            }
        }
    }
    public void BtnAddRepeticaoExercicio(View v)
    {
        int linha = (Integer) v.getTag();
        Intent intent = new Intent();
        intent.setClass(this,TelaAddRepeticaoExercicio.class);
        intent.putExtra("linha", linha);
        intent.putExtra("nomeTreino", nmTreino);
        intent.putExtra("nomeExercicio", retornarInfoExercicioNaList.getNome(linha));
        intent.putExtra("idExercicio",Integer.parseInt(retornarInfoExercicioNaList.getId(linha)));
        intent.putExtra("nomeGrupo", descobrirGrupo(retornarInfoExercicioNaList.getIdGrupo(linha)));
        intent.putExtra("idTreino", Integer.parseInt(idTreino));
        startActivity(intent);
        finish();
    }
    private void atualizarListExercicio()
    {
        // Carregar a lista de exercício com os exercícios do treino correspondente
        carregarExerciciosTreino();
        if(listEntDomExercicio != null)
        {
            int indTela = 3; //TelaTreino
            lvTreinoExercicio.setAdapter(new ExercicioBaseAdapter(this, results, null, indTela, vetIDExe,vetRepeticoesExe));
        }
        else
        {
            lvTreinoExercicio.setAdapter(null);
        }
    }
    private String descobrirGrupo(int idGrupo)
    {
        GrupoMuscular grupoMuscular = new GrupoMuscular();
        grupoMuscular.setID(String.valueOf(idGrupo));
        List<EntidadeDominio> listAux = grupoMuscular.operar(this,true,Controler.DF_CONSULTAR, grupoMuscular);
        grupoMuscular = (GrupoMuscular)listAux.get(0);
        return grupoMuscular.getNome();
    }
    public void onBackPressed() // voltar?
    {
        Intent intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
        intent.setClass(TelaTreinoExercicio.this, TelaPrincipalTreino.class);
        startActivity(intent); // chama a próxima tela(tela anterior)
        finish();
    }
}//end Activity
