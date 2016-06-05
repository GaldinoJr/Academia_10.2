package com.example.galdino.academia_102.Telas.TelaTreinoPorGrupo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.galdino.academia_102.BaseAdapter.ExercicioBaseAdapter;
import com.example.galdino.academia_102.Controler.Controler;
import com.example.galdino.academia_102.Dominio.Abas.ClsTabTreinoPorGrupo;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;
import com.example.galdino.academia_102.Dominio.Exercicio;
import com.example.galdino.academia_102.Dominio.GrupoMuscular;
import com.example.galdino.academia_102.Dominio.RetornarInfoExercicioNaList;
import com.example.galdino.academia_102.Dominio.Treino;
import com.example.galdino.academia_102.Dominio.TreinoExercicio;
import com.example.galdino.academia_102.Dominio.TreinoExercicioRepeticao;
import com.example.galdino.academia_102.R;
import com.example.galdino.academia_102.Telas.TelaAddRepeticaoExercicio;
import com.example.galdino.academia_102.Telas.TelaExercicio.TabPrincipalExercicio;
import com.example.galdino.academia_102.Telas.TelaListaExercicios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Galdino on 15/05/2016.
 */
public class FragTab2Exercicios extends Fragment
{
    private ListView lvTreinoExercicio;
    private List<EntidadeDominio> listEntDomExercicio;
    private FloatingActionButton fBtnAddEx;
    private String idTreino;
    //private TextView lblNmTreino;
    private ArrayList<String> vetIDExe;
    private ArrayList<String> vetRepeticoesExe;
    private ArrayList<Exercicio> results;
    private RetornarInfoExercicioNaList retornarInfoExercicioNaList;
    private Treino treino;
    private GrupoMuscular grupoMuscular;
    private ArrayList<String> listaCodigosObj;
    private ArrayList<String> listaCodigosNivel;
    private ClsTabTreinoPorGrupo tabTreinoPorGrupo = ClsTabTreinoPorGrupo.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_treino_2_exercicios,container,false);
        // associa os objetos da tela
        lvTreinoExercicio = (ListView)v.findViewById(R.id.lvTreinoExercicio);
        fBtnAddEx = (FloatingActionButton)v.findViewById(R.id.fBtnAddExercicio
                // FLOAT BUTTON
        );
        fBtnAddEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamarTelaCorrespondeteAoGrupo(grupoMuscular.getNome());
            }
        });
        // cria a intenção que vai receber os dados da tela 1
        Intent dados = getActivity().getIntent();
        // Os itens recebidos pela activity que "guarda" os fragments tambem podem ser acessados pelos fragments, que são como "filhos"
        idTreino = dados.getStringExtra("idTreino");
        listaCodigosObj = dados.getStringArrayListExtra("listaCodigosObj");
        listaCodigosNivel = dados.getStringArrayListExtra("listaCodigosNivel");
        treino = tabTreinoPorGrupo.getTreino();
        if(carregarGrupoMuscular() == -1)
        {
            Toast.makeText(getContext(),"ERRO: Grupo muscular não encontrado.",Toast.LENGTH_LONG).show();
            //return;
        }
        if(treino.getFgCarga() == 1)
            fBtnAddEx.setVisibility(View.INVISIBLE);
        // Carregar a lista de exercício com os exercícios do treino correspondente
        atualizarListExercicio();
        // monta a lista
        if(listEntDomExercicio != null) {
            final Context context = getContext();

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
                    intent.setClass(getActivity(), TabPrincipalExercicio.class);
                    intent.putExtra("nmGrupo", grupo);
                    intent.putExtra("nmGifExercicio", NomeLogico);
                    intent.putExtra("nmExercicio", exe);

                    startActivity(intent); // chama a próxima tela
                }
            });
        }

        return v;
    }
    public void chamarTelaCorrespondeteAoGrupo(String grupo)
    {
        Intent intent = new Intent();
        // Para chamar a próxima tela tem que dizer qual e a tela atual, e dpois a próxima tela( a que vai ser chamada)
        intent.setClass(getActivity(), TelaListaExercicios.class);
        intent.putExtra("grupo", grupo);
        intent.putExtra("nmTelaCorrespondente", FragTab2Exercicios.class.toString());
        //if(TelaTreinoExercicio.class.toString().equals(telaAnterior)) {
        intent.putExtra("idTreino", idTreino);
        intent.putExtra("nmTreino", treino.getNome());
        intent.putStringArrayListExtra("exe", (ArrayList<String>) vetIDExe);
//            Bundle b=new Bundle();
//            b.putStringArray("exe", vetIDExe);
//            intent.putExtras(b);
        //}
        startActivity(intent); // chama a próxima tela
        getActivity().finish(); // Encerra a tela atual*******************************************
    }
    private String carregarRepeticaoExercicio(String idTreinoExercicio)
    {
        TreinoExercicioRepeticao treinoExercicioRepeticao = new TreinoExercicioRepeticao();
        treinoExercicioRepeticao.setID(idTreinoExercicio);
        List<EntidadeDominio> ledTer = treinoExercicioRepeticao.operar(getContext(),true,Controler.DF_CONSULTAR,treinoExercicioRepeticao);
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
        listEntDomTreinoExercicio = treinoExercicio.operar(getContext(),true,Controler.DF_CONSULTAR,treinoExercicio);
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
                List<EntidadeDominio> listEntDomExe = exercicio.operar(getContext(),true,Controler.DF_CONSULTAR,exercicio);
                exercicio = (Exercicio)listEntDomExe.get(0);
                results.add(exercicio);
                listEntDomExercicio.add(exercicio);
                vetIDExe.add(exercicio.getID());
                vetRepeticoesExe.add(carregarRepeticaoExercicio(te.getID()));
            }
            retornarInfoExercicioNaList = new RetornarInfoExercicioNaList(listEntDomExercicio);
        }
    }

    private int carregarGrupoMuscular()
    {
        List<EntidadeDominio> listEntDom;
        grupoMuscular = new GrupoMuscular();
        grupoMuscular.setID(treino.getIdGrupo().toString());
        listEntDom = grupoMuscular.operar(getContext(),true,Controler.DF_CONSULTAR,grupoMuscular);
        if(listEntDom != null)
        {
            grupoMuscular = (GrupoMuscular)listEntDom.get(0);
            return 0;
        }
        else
            return -1;
    }
    private void atualizarListExercicio()
    {
        // Carregar a lista de exercício com os exercícios do treino correspondente
        carregarExerciciosTreino();
        if(listEntDomExercicio != null)
        {
            int indTela = 3; //TelaTreino
            ExercicioBaseAdapter teste = new ExercicioBaseAdapter(getContext(), results, null, indTela, vetIDExe,vetRepeticoesExe, treino.getFgCarga());
            lvTreinoExercicio.setAdapter(teste);
        }
        else
        {
            lvTreinoExercicio.setAdapter(null);
        }
    }
    public void BtnExcluirExercicioTreino_fragment(View v)
    {
//        String teste = idTreino;
//        switch (v.getId())
//        {
//            case R.id.telaTreinoExercicio:
//                int i = 0;
//                i++;
//                break;
//        }
        // VERIFICAR SE CONFIRMA A EXCLUSÃO DO EXERCÍCIO
        if(listEntDomExercicio != null)
        {
            try {
                int linha = (Integer) v.getTag();
                TreinoExercicio treinoExercicioExclui = new TreinoExercicio();
                treinoExercicioExclui.setIdTreino(Integer.parseInt(idTreino));
                treinoExercicioExclui.setIdExercicio(Integer.parseInt(retornarInfoExercicioNaList.getId(linha)));
                treinoExercicioExclui.operar(getContext(), true, Controler.DF_EXCLUIR, treinoExercicioExclui);
                atualizarListExercicio();
                Toast.makeText(getActivity(),"Exercício removido do treino.",Toast.LENGTH_LONG).show();
                return;
            }
            catch (Exception e)
            {
                Toast.makeText(getActivity(),"Erro ao excluir o exercício.",Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    public void BtnAddRepeticaoExercicio_fragment(View v)
    {
        //String teste = idTreino;
//        switch (v.getId())
//        {
//            case R.id.telaTreinoExercicio:
//                int i = 0;
//                i++;
//                break;
//        }
        int linha = (Integer) v.getTag();
        Intent intent = new Intent();
        intent.setClass(getContext(), TelaAddRepeticaoExercicio.class);
        intent.putExtra("linha", linha);
        intent.putExtra("nomeTreino", treino.getNome());
        intent.putExtra("nomeExercicio", retornarInfoExercicioNaList.getNome(linha));
        intent.putExtra("idExercicio",Integer.parseInt(retornarInfoExercicioNaList.getId(linha)));
        //intent.putExtra("nomeGrupo", descobrirGrupo(retornarInfoExercicioNaList.getIdGrupo(linha)));
        intent.putExtra("nomeGrupo",grupoMuscular.getNome());
        intent.putExtra("idTreino", Integer.parseInt(idTreino));

        startActivity(intent);
        getActivity().finish();
    }
}
