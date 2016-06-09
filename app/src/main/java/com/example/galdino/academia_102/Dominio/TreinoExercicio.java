package com.example.galdino.academia_102.Dominio;

/**
 * Created by Galdino on 27/02/2016.
 */
public class TreinoExercicio extends EntidadeDominio{
    private Integer idTreino,
                idExercicio,
                nrOrdem;

    // sets
    public void setIdTreino(Integer idTreino) {
        this.idTreino = idTreino;
    }
    public void setIdExercicio(Integer idExercicio) {
        this.idExercicio = idExercicio;
    }
    public void setNrOrdem(Integer nrOrdem) {
        this.nrOrdem = nrOrdem;
    }

    // gets
    public Integer getIdTreino() {
        return idTreino;
    }
    public Integer getIdExercicio() {
        return idExercicio;
    }
    public Integer getNrOrdem() {
        return nrOrdem;
    }
}
