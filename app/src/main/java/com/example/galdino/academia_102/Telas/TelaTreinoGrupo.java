package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.galdino.academia_102.BaseAdapter.ListaTreinosBaseAdapter;
import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.GrupoMuscular;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.R;

import java.util.ArrayList;
import java.util.List;

public class TelaTreinoGrupo extends AppCompatActivity implements View.OnClickListener
{
    private int i;
    private Button btnAddTreino;
    private EditText txtNomeTreino;
    private ListView listTreinos;
    private List<EntidadeDominio> listEntDomTreinos;
    private Treino treino;
    private GrupoMuscular grupoMuscular;
    private List<EntidadeDominio> listEntDom;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_treino_grupo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
//        FloatingActionButton fBtnClick = (FloatingActionButton) findViewById(R.id.fBtnClick);
//        fBtnClick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        //
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED); // Para não iniciar o teclado quando abrir a tela
        //
        btnAddTreino = (Button)findViewById(R.id.btnAddTreino);
        txtNomeTreino = (EditText)findViewById(R.id.txtNomeTreino);
        listTreinos = (ListView)findViewById(R.id.listTreinos);
        //
        btnAddTreino.setOnClickListener(this);
        //
        Intent dados = getIntent();
        String grupo = dados.getStringExtra("grupo");
        grupoMuscular = new GrupoMuscular();
        grupoMuscular.setNome(grupo);
        listEntDom = grupoMuscular.operar(this,true,Controler.DF_CONSULTAR,grupoMuscular);
        if(listEntDom != null)
            grupoMuscular = (GrupoMuscular)listEntDom.get(0);
        else
        {
            Toast.makeText(TelaTreinoGrupo.this, "Grupo Muscular não encontrado.", Toast.LENGTH_SHORT).show();
            return;
        }
        atualizarListTreinos();
        //
        listTreinos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> a, View v, final int position, long id)
            {
                Object o = listTreinos.getItemAtPosition(position);
                Intent intent = new Intent();
                // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
                intent.setClass(TelaTreinoGrupo.this, TelaTreinoExercicio.class);
                intent.putExtra("idTreino", retornarInfoTreino(position, 0));
                intent.putExtra("nomeTreino", retornarInfoTreino(position, 1));
                intent.putExtra("fgCarga", retornarInfoTreino(position, 2));
                intent.putExtra("nmGrupo", grupoMuscular.getNome());
                startActivity(intent); // chama a próxima tela
                finish();

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED); // Para não iniciar o teclado quando abrir a tela
        return true;
    }

    private List<EntidadeDominio> carregarTreinos()
    {
        List<EntidadeDominio> listEntDom;
        treino = new Treino();
        treino.setIdGrupo(Integer.parseInt(grupoMuscular.getID()));
        //List<Treino> lTreino = new LinkedList<Treino>();
        //lTreino = treino.converteEntidadeEmClasse(treino.operar(this,true, Controler.DF_CONSULTAR,treino), Treino.class);
        //if(lTreino == null)
        //return;
        listEntDom = treino.operar(this,true, Controler.DF_CONSULTAR,treino);
        if(listEntDom == null)
            return null;
        else if(listEntDom.isEmpty())
            return null;
        else
            return listEntDom;
    }

    private void atualizarListTreinos()
    {
        List<EntidadeDominio> listEntDom = carregarTreinos();
        listEntDomTreinos = listEntDom;
        ArrayList<Treino> results = new ArrayList<Treino>();
        ListView lvTreinos = (ListView)findViewById(R.id.listTreinos);

        if(listEntDom == null) {
            lvTreinos.setAdapter(null);
            return;
        }
        String[] vetSTreino = new String[listEntDom.size()];
        for(i = 0; i< listEntDom.size();i++) {
            Treino t = (Treino)listEntDom.get(i);
            vetSTreino[i] = t.getNome();
            results.add(t);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, vetSTreino);
        listTreinos.setAdapter(adapter);
        ArrayList<Treino> image_details2 = results;
        lvTreinos.setAdapter(new ListaTreinosBaseAdapter(this, image_details2,listEntDom));
    }
    @Override
    public void onClick(View view) {
        if(view == btnAddTreino)
        { // vai add um novo treino?
            String nomeTreino;
            nomeTreino = txtNomeTreino.getText().toString();
            if(nomeTreino.equals("")) // não digitou nome nenhum?
            {
                Toast.makeText(TelaTreinoGrupo.this, "Digite um nome para o treino", Toast.LENGTH_SHORT).show();
                return;
            }
            // se chegou até aqui digitou um nome, então cadastrar o novo treino no banco, e chamar o método para atualizar a list
            treino = new Treino();
            treino.setNome(nomeTreino);
            treino.setFgCarga(0);
            treino.setIdGrupo(Integer.parseInt(grupoMuscular.getID()));
            treino.operar(this, true, Controler.DF_SALVAR, treino);
            atualizarListTreinos();
            txtNomeTreino.setText("");
        }

    }
    public void onBackPressed() // voltar?
    {

        Intent intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e depois a próxima tela( a que vai ser chamada)
        intent.setClass(TelaTreinoGrupo.this, TelaCorpoTreino.class);
        startActivity(intent); // chama a próxima tela(tela anterior)
        finish();

    }
    public void BtnExclui(View v)
    {
        int linha = (Integer) v.getTag(); // linha clicada da list(foi setado no ListaTreinosbaseAdapter - > holder.itemImage.setTag(position);
        excluirTreino(linha);
        atualizarListTreinos();

    }
    public void excluirTreino(int linha)
    {
        if(listEntDomTreinos != null) {
            Treino treino = (Treino) listEntDomTreinos.get(linha);
            treino.operar(this,true, Controler.DF_EXCLUIR,treino);
        }
    }
    public String retornarInfoTreino(int linha, int coluna)
    {
        if(listEntDomTreinos != null) {
            Treino treino = (Treino) listEntDomTreinos.get(linha);
            if(coluna == 0)
                return treino.getID();
            else if(coluna == 1)
                return treino.getNome();
            else if(coluna == 2)
                return treino.getFgCarga().toString();
        }
        return "Sem informação";
    }
}
