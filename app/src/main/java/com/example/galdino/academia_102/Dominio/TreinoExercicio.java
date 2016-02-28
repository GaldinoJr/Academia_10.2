package com.example.galdino.academia_102.Dominio;

/**
 * Created by Galdino on 27/02/2016.
 */
public class TreinoExercicio extends EntidadeDominio{
    private int idTreino,
                idExercicio,
                nrRepeticoes;

    // sets
    public void setIdTreino(int idTreino) {
        this.idTreino = idTreino;
    }
    public void setIdExercicio(int idExercicio) {
        this.idExercicio = idExercicio;
    }
    public void setNrRepeticoes(int nrRepeticoes) {
        this.nrRepeticoes = nrRepeticoes;
    }

    // gets
    public int getIdTreino() {
        return idTreino;
    }
    public int getIdExercicio() {
        return idExercicio;
    }
    public int getNrRepeticoes() {
        return nrRepeticoes;
    }
}
