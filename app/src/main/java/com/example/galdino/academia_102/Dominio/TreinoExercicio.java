package com.example.galdino.academia_102.Dominio;

/**
 * Created by Galdino on 27/02/2016.
 */
public class TreinoExercicio extends EntidadeDominio{
    private Integer idTreino,
                idExercicio,
                nrRepeticoes;

    // sets
    public void setIdTreino(Integer idTreino) {
        this.idTreino = idTreino;
    }
    public void setIdExercicio(Integer idExercicio) {
        this.idExercicio = idExercicio;
    }
    public void setNrRepeticoes(Integer nrRepeticoes) {
        this.nrRepeticoes = nrRepeticoes;
    }

    // gets
    public Integer getIdTreino() {
        return idTreino;
    }
    public Integer getIdExercicio() {
        return idExercicio;
    }
    public Integer getNrRepeticoes() {
        return nrRepeticoes;
    }
}
