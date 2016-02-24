package com.example.galdino.academia_102.Core.Command;

import com.example.galdino.academia_102.Core.Aplicacao.Resultado;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;

/**
 * Created by Galdino on 14/02/2016.
 */
public interface IComand {
    public Resultado execute(EntidadeDominio entidade);
}

