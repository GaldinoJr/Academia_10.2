package com.example.galdino.academia_102.Telas.TelaExercicioAntiga;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.galdino.academia_102.Dominio.Documento;
import com.example.galdino.academia_102.R;

/**
 * Created by Galdino on 03/04/2016.
 */
public class TelaExercicioAba2  extends Activity
{
    private TextView txtPrimario,
                    txtSecundario;
    private String  primario,
                    secundario,
                    nome,
                    grupo;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_exercicio_aba_2_musculos);
        //
        txtPrimario =(TextView)findViewById(R.id.txtPrimario2);
        txtSecundario = (TextView)findViewById(R.id.txtSecundario);
        //
        Intent dados = getIntent();
        // Recebe os dados da tela anterior
        nome = dados.getStringExtra("nome");
        grupo = dados.getStringExtra("grupo");
        //
        Documento documento = new Documento(this);
        primario =  documento.carregarArquivoTxt(grupo, nome, "Princ");
        if(primario == null)
            primario = txtPrimario.getText().toString();
        secundario = documento.carregarArquivoTxt(grupo, nome, "Sec");
        if(secundario == null)
            secundario = txtSecundario.getText().toString();
        //
        txtPrimario.setText(primario);
        txtSecundario.setText(secundario);
    }
}

