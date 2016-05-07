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
        gravarTreinos(context);
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

    private void gravarTreinos(Context context)
    {
        String doc = "Treino";
        GrupoMuscular grupoMuscular = new GrupoMuscular();
        List<EntidadeDominio> lEntDom = grupoMuscular.operar(context,true,Controler.DF_CONSULTAR,grupoMuscular);
        for(int i = 0; i < 1; i++)//mudar conforme add treino
        {
            int indice = i+1;
            doc = doc + indice;
            Documento documento = new Documento(context);
            for(EntidadeDominio entDom : lEntDom )
            {
                GrupoMuscular g = (GrupoMuscular)entDom;
                String nomeGrupo = g.getNome();
                String exerciciosJuntos = documento.carregarArquivoTxt(nomeGrupo, doc, "Treinos","sim");
                if (exerciciosJuntos != null)
                {
                    Treino treino = new Treino();
                    String[] treinoCarga = exerciciosJuntos.split("&");
                    // Primeiro pega o nome
                    String nomeTreino = treinoCarga[0];
                    treino.setNome(nomeTreino);
                    // Tipo
                    String tipoTreino = treinoCarga[1];
                    if(tipoTreino != null || tipoTreino != "")
                        treino.setIndTipoTreino(Integer.parseInt(tipoTreino));
                    // Nível
                    String nivelTreino = treinoCarga[2];
                    if(nivelTreino != null || nivelTreino != "")
                        treino.setIndNivel(Integer.parseInt(nivelTreino));
                    // Descrição
                    String descricao = treinoCarga[3];
                    treino.setDescricao(descricao);
                    // Exercícios
                    String exercicios = treinoCarga[4];
                    String[] exerciciosSeparados = exercicios.split("@");

                    treino.setIdGrupo(Integer.parseInt(g.getID()));
                    treino.setFgCarga(1); // Indica que o treino veio através de carga.
                    List<EntidadeDominio> lauxTreino = treino.operar(context,true,Controler.DF_SALVAR,treino);
                    treino = (Treino)lauxTreino.get(0);
                    if(treino.getID()!= null)
                    {
                        for (int j = 0; j < (exerciciosSeparados.length - 1); j++)
                        {
                            Exercicio exercicio = new Exercicio();
                            String[] repeticoes = exerciciosSeparados[j].split(";");
                            String nomeExe = repeticoes[0];
                            encontrarNomes(nomeExe, nomeGrupo);
                            exercicio.setNomeLogico(nomeGif);
                            List<EntidadeDominio> laux = exercicio.operar(context, true, Controler.DF_CONSULTAR, exercicio);
                            if (laux != null) {
                                exercicio = (Exercicio) laux.get(0);
                                TreinoExercicio te = new TreinoExercicio();
                                te.setIdExercicio(Integer.parseInt(exercicio.getID()));
                                te.setIdTreino(Integer.parseInt(treino.getID()));
                                laux = te.operar(context, true, Controler.DF_SALVAR, te);
                                if (laux != null)
                                {
                                    te = (TreinoExercicio)laux.get(0);
                                    for (int k = 1; k < repeticoes.length; k++) {
                                        TreinoExercicioRepeticao treinoExercicioRepeticao = new TreinoExercicioRepeticao();
                                        treinoExercicioRepeticao.setNrRepeticoes(Integer.parseInt(repeticoes[k]));
                                        treinoExercicioRepeticao.setID(te.getID());
                                        treinoExercicioRepeticao.operar(context, true, Controler.DF_SALVAR, treinoExercicioRepeticao);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

//            primario = txtPrimario.getText().toString();
//            secundario = documento.carregarArquivoTxt(grupo, nome, "Sec");
//            if(secundario == null)
//            secundario = txtSecundario.getText().toString();
//            aux = documento.carregarArquivoTxt(grupo, nome, "Descr");
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

