package com.example.galdino.academia_102.Dominio;

/**
 * Created by Galdino on 27/02/2016.
 */
public class MusculoExercicio extends EntidadeDominio{
    private int idMusculo,
                idExercicio,
                indPrimarioSecundario; // 1 = primario, 2 = secundario

    // sets
    public void setIdMusculo(int idMusculo) {
        this.idMusculo = idMusculo;
    }
    public void setIdExercicio(int idExercicio) {
        this.idExercicio = idExercicio;
    }
    public void setIndPrimarioSecundario(int indPrimarioSecundario) {
        this.indPrimarioSecundario = indPrimarioSecundario;
    }

    // gets
    public int getIdMusculo() {
        return idMusculo;
    }
    public int getIdExercicio() {
        return idExercicio;
    }
    public int getIndPrimarioSecundario() {
        return indPrimarioSecundario;
    }
}
