package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.galdino.academia_102.BaseAdapter.ListaTreinosBaseAdapter;
import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.R.id;

import java.util.ArrayList;
import java.util.List;

public class TelaPrincipalFicha extends AppCompatActivity implements View.OnClickListener {

    private int i;
    private Button btnAddTreino;
    private EditText txtNomeTreino;
    private ListView listTreinos;
    private List<EntidadeDominio> listEntDomTreinos;
    //
    //private SQLtreino dbTreino;
    private Treino treino;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal_ficha);
        //
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED); // Para não iniciar o teclado quando abrir a tela
        //
        btnAddTreino = (Button)findViewById(id.btnAddTreino);
        txtNomeTreino = (EditText)findViewById(id.txtNomeTreino);
        listTreinos = (ListView)findViewById(id.listTreinos);
        //
        btnAddTreino.setOnClickListener(this);
        //
        atualizarListTreinos();
        //
        listTreinos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> a, View v, final int position, long id)
            {
                String nome;
                Object o = listTreinos.getItemAtPosition(position);
                nome = o.toString();
                //Toast.makeText(TelaPrincipalFicha.this, "nome: " + nome, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
                intent.setClass(TelaPrincipalFicha.this, TelaFichaListExercicios.class);
                intent.putExtra("nomeTreino", nome);
                startActivity(intent); // chama a próxima tela
                finish();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED); // Para não iniciar o teclado quando abrir a tela
        //getMenuInflater().inflate(R.menu.tela_principal_ficha, menu);
        return true;
    }

    private List<EntidadeDominio> carregarTreinos()
    {
        List<EntidadeDominio> listEntDom;
        treino = new Treino();
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

        if(listEntDom == null)
            return;
        String[] vetSTreino = new String[listEntDom.size()];
        for(i = 0; i< listEntDom.size();i++) {
            Treino t = (Treino)listEntDom.get(i);
            vetSTreino[i] = t.getNome();
            results.add(t);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, vetSTreino);

        listTreinos.setAdapter(adapter);
        final ListView lvTreinos = (ListView)findViewById(id.listTreinos);
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
                Toast.makeText(TelaPrincipalFicha.this, "Digite um nome para o treino", Toast.LENGTH_SHORT).show();
                return;
            }
            // se chegou até aqui digitou um nome, então cadastrar o novo treino no banco, e chamar o método para atualizar a list
            treino = new Treino();
            treino.setNome(nomeTreino);
            treino.operar(this, true, Controler.DF_SALVAR, treino);
            atualizarListTreinos();
            txtNomeTreino.setText("");
        }

    }
    public void onBackPressed() // voltar?
    {

        Intent intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e depois a próxima tela( a que vai ser chamada)
        intent.setClass(TelaPrincipalFicha.this, TelaPrincipalApp.class);
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
}