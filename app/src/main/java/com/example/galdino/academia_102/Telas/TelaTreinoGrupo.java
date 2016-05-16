package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.galdino.academia_102.Telas.TelaExercicio.TabPrincipalExercicio;
import com.example.galdino.academia_102.Telas.TelaTreinoPorGrupo.TabPrincipalTreinoPorGrupo;

import java.util.ArrayList;
import java.util.List;

public class TelaTreinoGrupo extends AppCompatActivity implements View.OnClickListener
{
    private int i;
    private Button btnAddTreino,
                    btnFiltroTreino;
    private EditText txtNomeTreino;
    private ListView listTreinos;
    private List<EntidadeDominio> listEntDomTreinos;
    private Treino treino;
    private GrupoMuscular grupoMuscular;
    private List<EntidadeDominio> listEntDom;
    private String grupo;
    // Dados da tela de filtro
    private String fgTelaFiltro;
    private ArrayList<String> listaCodigosObj;
    private ArrayList<String> listaCodigosNivel;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_treino_grupo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //funciona
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_menu_options_preto2);
        toolbar.setOverflowIcon(drawable);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED); // Para não iniciar o teclado quando abrir a tela
        // Vincula os dados da tela
        btnAddTreino = (Button)findViewById(R.id.btnAddTreino);
        btnFiltroTreino = (Button)findViewById(R.id.btnFiltroTreino);
        txtNomeTreino = (EditText)findViewById(R.id.txtNomeTreino);
        listTreinos = (ListView)findViewById(R.id.listTreinos);
        // Add o listenner nos botões
        btnAddTreino.setOnClickListener(this);
        btnFiltroTreino.setOnClickListener(this);
        // pega os dados da tela anterior
        Intent dados = getIntent();
        grupo = dados.getStringExtra("grupo");
        fgTelaFiltro = dados.getStringExtra("fgTelaFiltro");
        listaCodigosObj = dados.getStringArrayListExtra("listaCodigosObj");
        listaCodigosNivel = dados.getStringArrayListExtra("listaCodigosNivel");
        //
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
                //intent.setClass(TelaTreinoGrupo.this, TelaTreinoExercicio.class);
                intent.setClass(TelaTreinoGrupo.this, TabPrincipalTreinoPorGrupo.class);
                intent.putExtra("idTreino", retornarInfoTreino(position, 0));
                intent.putExtra("nmGrupo", grupoMuscular.getNome());
                if("1".equals(fgTelaFiltro)) // já foi para a tela de filtro?
                {
                    intent.putStringArrayListExtra("listaCodigosObj", (ArrayList<String>) listaCodigosObj);
                    intent.putStringArrayListExtra("listaCodigosNivel", (ArrayList<String>) listaCodigosNivel);
                }
                startActivity(intent); // chama a próxima tela
                finish();

            }
        });
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED); // Para não iniciar o teclado quando abrir a tela
//        getMenuInflater().inflate(R.menu.menu_tela_treino_grupo, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menuDefinicao:
//                Toast.makeText(TelaTreinoGrupo.this, "Definição", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.menuForca:
//                Toast.makeText(TelaTreinoGrupo.this, "Força", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.menuHipertrofia:
//                Toast.makeText(TelaTreinoGrupo.this, "Hipertrofia", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.menuResistencia:
//                Toast.makeText(TelaTreinoGrupo.this, "Resistencia", Toast.LENGTH_SHORT).show();
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    // Nome - carregarTreinos
    // Função - Método que carrega os treinos de acordo com os filtros, se vier da tela de filtros,
    //se não, deve carregar todos os treinos
    // Retorno - Lista de treinos filtrados
    private List<EntidadeDominio> carregarTreinos()
    {
        List<EntidadeDominio> listEntDom;
        treino = new Treino();
        treino.setIdGrupo(Integer.parseInt(grupoMuscular.getID()));
        //List<Treino> lTreino = new LinkedList<Treino>();
        //lTreino = treino.converteEntidadeEmClasse(treino.operar(this,true, Controler.DF_CONSULTAR,treino), Treino.class);
        //if(lTreino == null)
        //return;
//        if("1".equals(fgTelaFiltro))
//        {
            treino.setListaCodigosObjParaBusca(listaCodigosObj);
            treino.setListaCodigosNivelParaBusca(listaCodigosNivel);
//        }
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
        if(view == btnFiltroTreino)
        {
            Intent intent = new Intent();
            intent.putExtra("grupo",grupo);
//            if("1".equals(fgTelaFiltro)) // já foi para a tela de filtro?
//            {
//                intent.putExtra("fgSegundaVez","1");
                intent.putStringArrayListExtra("listaCodigosObj", (ArrayList<String>) listaCodigosObj);
                intent.putStringArrayListExtra("listaCodigosNivel", (ArrayList<String>) listaCodigosNivel);
//            }
            intent.setClass(TelaTreinoGrupo.this, TelaFitroDeTreino.class);
            startActivity(intent); // chama a próxima tela(tela anterior)
            finish();
        }
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
            treino.operar(this, true, Controler.DF_EXCLUIR, treino);
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

    public void onBackPressed() // voltar?
    {

        Intent intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e depois a próxima tela( a que vai ser chamada)
        intent.setClass(TelaTreinoGrupo.this, TelaCorpoTreino.class);
        startActivity(intent); // chama a próxima tela(tela anterior)
        finish();

    }
}
