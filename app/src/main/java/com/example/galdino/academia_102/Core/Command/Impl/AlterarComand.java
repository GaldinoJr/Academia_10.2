package com.example.galdino.academia_102.Core.Command.Impl;




import com.example.galdino.academia_102.Core.Aplicacao.Resultado;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;

import java.util.List;

/**
 * Created by Galdino on 20/08/2015.
 */
public class AlterarComand extends AbstractComand {
    public Resultado execute(EntidadeDominio entidade) {
        return fachada.alterar(entidade);
    }
    public List<EntidadeDominio> consulta(EntidadeDominio entidade) {
        return null;
    }
}
