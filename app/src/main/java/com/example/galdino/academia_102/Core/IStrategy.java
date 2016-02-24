package com.example.galdino.academia_102.Core;

import com.example.galdino.academia_102.Dominio.EntidadeDominio;

/**
 * Created by Galdino on 20/08/2015.
 */
public interface IStrategy {
    public String processar(EntidadeDominio entidade);
}
