package com.example.galdino.academia_102.Dominio;

import android.content.Context;

import com.example.galdino.academia_102.Controler.Controler;

import java.util.List;

/**
 * Created by Galdino on 28/02/2016.
 */
public class IniciarBancoExercicios2 {
    private int qtdGrupos,
            i;
    private String[] grupos;
    private GrupoMuscular grupoMuscular;
    private String
                nomeGif,
                nomeFoto;
    public IniciarBancoExercicios2()
    {
        grupoMuscular = new GrupoMuscular();
        grupos = grupoMuscular.getVetorGrupos();
    }

    public void criar(Context context)
    {
        gravarGrupos(context);
        gravarExercicios(context);
    }
    private void gravarGrupos(Context context)
    {
        qtdGrupos = grupos.length;
        for(i = 0; i < qtdGrupos; i++)
        {
            grupoMuscular.setNome(grupos[i]);
            grupoMuscular.operar(context,true, Controler.DF_SALVAR,grupoMuscular);
        }
    }
    private void gravarExercicios(Context context)
    {

        int qtdExercicio;
        String[] vetorExercicio;
        String grupo;
        Exercicio exercicio = new Exercicio();
        List<EntidadeDominio> listEntDom;
        Documento documento = new Documento(context);
        for(i = 0; i < qtdGrupos; i++)
        {
            exercicio.ordenarVetores(grupos[i]);
            vetorExercicio = exercicio.getVetCorrespondente();
            qtdExercicio = vetorExercicio.length;
            GrupoMuscular g = new GrupoMuscular();
            grupo = grupos[i];
            g.setNome(grupo);
            listEntDom = grupoMuscular.operar(context,true,Controler.DF_CONSULTAR,g);
            if(listEntDom != null) //Achou alguma coisa?
            {
                g = (GrupoMuscular) listEntDom.get(0);
                for (int j = 0; j < qtdExercicio; j++)
                {

                    String nome = vetorExercicio[j];
                    encontrarNomes(nome, grupo);
                    exercicio.setNome(nome);
                    exercicio.setNomeLogico(nomeGif);
                    exercicio.setNomeLogicoFoto(nomeFoto);
                    exercicio.setDescricao(documento.carregarArquivoTxt(grupo, nomeGif, "Descr"));
                    exercicio.setIdGrupo(Integer.parseInt(g.getID()));
                    exercicio.operar(context, true, Controler.DF_SALVAR, exercicio);

                    // POPULAR TAMBEM AS TABELAS MUSCULO E MUSCULO_EXERCICIO
                    //    Documento documento = new Documento(this);
                    //    primario =  documento.carregarArquivoTxt(grupo, nome, "Princ");
                    //    if(primario == null)
                    //    primario = txtPrimario.getText().toString();
                    //    secundario = documento.carregarArquivoTxt(grupo, nome, "Sec");
                    //    if(secundario == null)
                    //    secundario = txtSecundario.getText().toString();
                    //    aux = documento.carregarArquivoTxt(grupo, nome, "Descr");
                }
            }
        }// end for I
        Configuracao configuracao = new Configuracao();
        configuracao.setFgBancoCriado(1);
        configuracao.operar(context,true,Controler.DF_SALVAR,configuracao);
    }

    private void encontrarNomes(String nome, String grupo)
    {
        int tamanho,
                i,
                j;

        String gifNome = "";
        String fotoNome = grupo.toLowerCase() + "_";
        tamanho = nome.length(); // recebe a quantidade de caracteres da palavra
        for(i = 0, j = 0; j < tamanho; i++, j++) // Roda toda  string
        {
            // monta o nome do gif
            if(i < tamanho)
            {
                if (nome.charAt(i) == ' ') // Achou espaço?
                {
                    gifNome += String.valueOf(nome.charAt(i + 1)).toUpperCase(); // sim pula ele e muda o próximo caracter para maiusculo concatenando na nova string
                    i++; // incrementa o indice
                }
                else
                    gifNome += nome.charAt(i); // apenas concatena a letra na string

            }
            // monta o nome da foto
            if(nome.charAt(j) == ' ') // Achou espaço?
                fotoNome += "_";
            else
                fotoNome += String.valueOf(nome.charAt(j)).toLowerCase(); // apenas concatena a letra  na string
        }
        gifNome = "gif" + gifNome; // Concatena o nome obtido com gif na frente, a fim de terminar o nome
        nomeGif = gifNome;
        nomeFoto = fotoNome;
    }

//    private String encontrarNome(String nome)
//    {
//        int tamanho,
//                i;
//
//        String gifNome = "";
//        tamanho = nome.length(); // recebe a quantidade de caracteres da palavra
//        for(i = 0; i< tamanho; i++) // Roda toda  string
//        {
//            if(nome.charAt(i) == ' ') // Achou espaço?
//            {
//                gifNome += String.valueOf(nome.charAt(i +1)).toUpperCase(); // sim pula ele e muda o próximo caracter para maiusculo concatenando na nova string
//                i++; // incrementa o indice
//            }
//            else
//                gifNome += nome.charAt(i); // apenas concatena na string
//        }
//        gifNome = "gif" + gifNome; // Concatena o nome obtido com gif na frente, a fim de terminar o nome
//        return gifNome;
//    }
}

