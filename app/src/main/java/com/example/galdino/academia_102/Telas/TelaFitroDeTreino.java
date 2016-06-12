package com.example.galdino.academia_102.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.example.galdino.academia_102.Core.Impl.Controle.Session;
import com.example.galdino.academia_102.Dominio.Filtro.clsFiltroTreino;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.R;

import java.util.ArrayList;
import java.util.List;

public class TelaFitroDeTreino extends AppCompatActivity implements View.OnClickListener {
    private String grupo;
    private Button btnBuscarTreinoComFiltro,
                    btnToolbarLimpar;
    private ImageButton iBtnVoltar;
    private CheckBox chkFeminino,
                    chkMasculino,
                    chkObjDefinicao,
                    chkObjForca,
                    chkObjHipertrofia,
                    chkObjResistencia,
                    chkNivelIniciante,
                    chkNivelIntermediario,
                    chkNivelAvancado;
    private List<String> lObj;
    private List<String> lNivel;
    private int sexo;
    private clsFiltroTreino filtroTreino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_fitro_de_treino);
        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //
        btnBuscarTreinoComFiltro = (Button)findViewById(R.id.btnBuscarTreinoComFiltro);
        btnToolbarLimpar = (Button)findViewById(R.id.btnToolbarLimpar );
        iBtnVoltar = (ImageButton)findViewById(R.id.iBtnVoltarDaTelaFiltro);
        chkFeminino = (CheckBox)findViewById(R.id.chkFeminino);
        chkMasculino = (CheckBox)findViewById(R.id.chkMasculino);
        chkObjDefinicao = (CheckBox)findViewById(R.id.chkObjDefinicao);
        chkObjForca = (CheckBox)findViewById(R.id.chkObjForca);
        chkObjHipertrofia = (CheckBox)findViewById(R.id.chkObjHipertrofia);
        chkObjResistencia = (CheckBox)findViewById(R.id.chkObjResistencia);
        chkNivelIniciante = (CheckBox)findViewById(R.id.chkNivelIniciante);
        chkNivelIntermediario = (CheckBox)findViewById(R.id.chkNivelIntermediario);
        chkNivelAvancado = (CheckBox)findViewById(R.id.chkNivelAvancado);

        btnBuscarTreinoComFiltro.setOnClickListener(this);
        btnToolbarLimpar.setOnClickListener(this);
        iBtnVoltar.setOnClickListener(this);
        //
        Intent dados = getIntent();
        grupo = dados.getStringExtra("grupo");
        carregarFiltrosSelecionadosAnteriormente();
    }

    @Override
    public void onClick(View view)
    {
        if(view == btnBuscarTreinoComFiltro)
            voltar(true);
        if(view == btnToolbarLimpar)
            limparFiltros();
        if(view == iBtnVoltar)
            voltar(false);
    }
    //**********************************
    // Nome - Voltar
    // Função - Quando o método voltar for do botão buscar, já deve vincular o código dos itens( que foram descritos
    //na classe de dominio Treino)
    // Quando apenas voltar, não atualiza os filtros, ou seja, filtra como era antes.
    //**********************************
    private void voltar(boolean fgBuscar)
    {
        Intent intent = new Intent();
        intent.putExtra("grupo", grupo);
        intent.putExtra("fgTelaFiltro", "1");
        if(fgBuscar)
        {
            lObj = new ArrayList<>();
            lNivel = new ArrayList<>();
            sexo = 3;
            // Sexo
            if (chkFeminino.isChecked())
                sexo = 0;
            if (chkMasculino.isChecked())
            {
                if(sexo == 0)
                    sexo = 3;
                else
                    sexo = 1;
            }
            // Filtros - Objetivo
            if (chkObjDefinicao.isChecked())
                lObj.add(Treino.DF_CD_OBJ_DEFINICAO);
            if (chkObjForca.isChecked())
                lObj.add(Treino.DF_CD_OBJ_FORCA);
            if (chkObjHipertrofia.isChecked())
                lObj.add(Treino.DF_CD_OBJ_HIPERTROFIA);
            if (chkObjResistencia.isChecked())
                lObj.add(Treino.DF_CD_OBJ_RESISTENCIA);
            // Nível
            if (chkNivelIniciante.isChecked())
                lNivel.add(Treino.DF_CD_NIVEL_INICIANTE);
            if (chkNivelIntermediario.isChecked())
                lNivel.add(Treino.DF_CD_NIVEL_INTERMEDIARIO);
            if (chkNivelAvancado.isChecked())
                lNivel.add(Treino.DF_CD_NIVEL_AVANCADO);
            //
            if(lObj.size() > 0 || lNivel.size() > 0 || sexo != 3)
                filtroTreino.setFgFiltrado(true);
            else
                filtroTreino.setFgFiltrado(false);
        }
        filtroTreino.getTreino().setListaCodigosObjParaBusca(lObj);
        filtroTreino.getTreino().setListaCodigosNivelParaBusca(lNivel);
        filtroTreino.getTreino().setIndSexo(sexo);
        //
        intent.setClass(TelaFitroDeTreino.this, TelaTreinoGrupo.class);
        startActivity(intent); // chama a próxima tela(tela anterior)
        finish();
    }

    // Nome - carregarFiltrosSelecionadosAnteriormente
    // Funçao - Carregar os filtros que foram selecionados anteriormente (quando deixou a tela pela última vez).
    // retorno - void
    private void carregarFiltrosSelecionadosAnteriormente()
    {
        filtroTreino = clsFiltroTreino.getInstance();
        sexo = filtroTreino.getTreino().getIndSexo();
        lObj = filtroTreino.getTreino().getListaCodigosObjParaBusca();
        lNivel = filtroTreino.getTreino().getListaCodigosNivelParaBusca();

        // Carrega o sexo
        if(sexo == 0 || sexo == 3)
            chkFeminino.setChecked(true);
        if(sexo == 1 || sexo == 3)
            chkMasculino.setChecked(true);
        // Carrega os objetivos
        if(lObj != null)
        {
            if(lObj.size() > 0)
            {
                if(lObj.contains(Treino.DF_CD_OBJ_DEFINICAO))
                    chkObjDefinicao.setChecked(true);
                if(lObj.contains(Treino.DF_CD_OBJ_FORCA))
                    chkObjForca.setChecked(true);
                if(lObj.contains(Treino.DF_CD_OBJ_HIPERTROFIA))
                    chkObjHipertrofia.setChecked(true);
                if(lObj.contains(Treino.DF_CD_OBJ_RESISTENCIA))
                    chkObjResistencia.setChecked(true);
            }
        }
        // Carrega os níveis
        if(lNivel != null)
        {
            if(lNivel.size() > 0)
            {
                if(lNivel.contains(Treino.DF_CD_NIVEL_INICIANTE))
                    chkNivelIniciante.setChecked(true);
                if(lNivel.contains(Treino.DF_CD_NIVEL_INTERMEDIARIO))
                    chkNivelIntermediario.setChecked(true);
                if(lNivel.contains(Treino.DF_CD_NIVEL_AVANCADO))
                    chkNivelAvancado.setChecked(true);
            }
        }
    }// end

    // Nome - limparFiltros
    // Função - Método que deve limpar todos os filtros da tela
    // Retorno - void
    private void limparFiltros()
    {
        chkMasculino.setChecked(true);
        chkFeminino.setChecked(true);
        chkObjDefinicao.setChecked(false);
        chkObjForca.setChecked(false);
        chkObjHipertrofia.setChecked(false);
        chkObjResistencia.setChecked(false);
        chkNivelIniciante.setChecked(false);
        chkNivelIntermediario.setChecked(false);
        chkNivelAvancado.setChecked(false);
    }
    public void onBackPressed() // voltar?
    {
        voltar(false);
    }
}
