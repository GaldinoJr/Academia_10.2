package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.Configuracao;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.IniciarBancoExercicios2;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.R.id;
import com.example.galdino.academia_102.Telas.TelaExercicio.TabPrincipalExercicio;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TelaPrincipalApp extends AppCompatActivity implements View.OnClickListener {

    private Button btnTelaExe,
            btnTelaFicha;
            //btnExemploMenu;
    private ImageButton iBtnExercicio,
                        iBtnTreino;
    private List<EntidadeDominio> listEntDom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal_app);
        //
        iBtnExercicio= (ImageButton)findViewById(id.iBtnExercicio);
        iBtnTreino = (ImageButton)findViewById(id.iBtnTreino);
        btnTelaFicha = (Button)findViewById(id.btnTelaFicha);
        btnTelaExe = (Button)findViewById(id.btnTelaExe);
        //btnExemploMenu = (Button)findViewById(id.btnExemploMenu); // Desativado, deixado apenas de exemplo
        //
        btnTelaExe.setOnClickListener(this);
        btnTelaFicha.setOnClickListener(this);
        //btnExemploMenu.setOnClickListener(this);
        iBtnExercicio.setOnClickListener(this);
        iBtnTreino.setOnClickListener(this);
        // Verifica se é a primeira execução do App no celular e cria o Banco de exercícios
        Configuracao configuracao = new Configuracao();

        listEntDom = configuracao.operar(this, true, Controler.DF_CONSULTAR, configuracao);
        if(listEntDom != null) {
            configuracao = (Configuracao) listEntDom.get(0);
            if(configuracao.getFgBancoCriado() != 1)
            {
                IniciarBancoExercicios2 inicarBancoExercicios = new IniciarBancoExercicios2();
                inicarBancoExercicios.criar(this);
            }
        }
        else
        {
            IniciarBancoExercicios2 inicarBancoExercicios = new IniciarBancoExercicios2();
            inicarBancoExercicios.criar(this);
        }
    }

  //  @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//       // getMenuInflater().inflate(R.menu.tela_principal02, menu);
//        return true;
//    }

    @Override
    public void onClick(View view) {
        if(view == iBtnExercicio)
        {
            Intent intent = new Intent();
            intent.setClass(TelaPrincipalApp.this, TelaSideMenu.class);
            startActivity(intent); // chama a próxima tela
            finish(); // Encerra a tela atual
        }
        if(view == iBtnTreino)
        {
            Intent intent = new Intent();
            intent.setClass(TelaPrincipalApp.this, TelaCorpoTreino.class);
            startActivity(intent); // chama a próxima tela
            finish(); // Encerra a tela atual
        }
        if(view == btnTelaExe)
        { // chama a tela principal de exercicios
            //File diretorio = new File(ObterDiretorio());

//            ArrayList<String> Arquivos = new ArrayList<String>();
//            File root = android.os.Environment.getExternalStorageDirectory();
//            String caminho = root.toString();
//
//            String teste;
//            File diretorio2;
//            File[] arquivos ;
//
//
//             String[] allFilesInPackage ;
//             String[] allFilesInPackage2;
//            try {
//                allFilesInPackage = this.getResources().getAssets().list("");
//                allFilesInPackage2 = this.getResources().getAssets().list("DocumentosTxt/Treinos/Peito");
//                int i = 0;
//                i ++;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Field[] fields= R.class.getFields();
//            for(int count=0; count < fields.length; count++){
//                String a = fields[count].getName();
//            }
//
//
//            teste = "com.example.galdino.academia/assets/DocumentosTxt/Peito";
//            diretorio2 = new File(teste);
//            arquivos = diretorio2.listFiles();
//
//
//
//            if(arquivos != null)
//            {
//                int length = arquivos.length;
//                for(int i = 0; i < length; ++i)
//                {
//                    File f = arquivos[i];
//                    if(f.isFile())
//                    {
//                        Arquivos.add(f.getName());
//                    }
//                }
//
////                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
////                        (this,android.R.layout.simple_dropdown_item_1line, Arquivos);
//               // SpnListarArquivos.setAdapter(arrayAdapter);
//            }
            Intent intent = new Intent();
            intent.setClass(TelaPrincipalApp.this, Teste.class);
            //intent.setClass(TelaPrincipalApp.this, TelaPrincipalExercicio.class);
            //intent.putExtra("exe", "");
            startActivity(intent); // chama a próxima tela
            finish(); // Encerra a tela atual
        }
        if(view == btnTelaFicha)
        { // chama a tela principal de fichas
//            Intent intent = new Intent();
//            intent.setClass(TelaPrincipalApp.this, TabPrincipalExercicio.class);
//            intent.putExtra("nmGifExercicio", "gifComGiro");
//            intent.putExtra("nmExercicio", "Com Giro");
//            intent.putExtra("nmGrupo", "Abdomen");
//            //intent.setClass(TelaPrincipalApp.this, TelaPrincipalTreino.class);
//            startActivity(intent); // chama a próxima tela
//            finish(); // Encerra a tela atual
        }
//        if(view == btnExemploMenu)
//        {
//            Intent intent = new Intent();
//            intent.setClass(TelaPrincipalApp.this, TelaListExercicioNova.class);
//            startActivity(intent); // chama a próxima tela
//            finish(); // Encerra a tela atual
//        }
    }

}
