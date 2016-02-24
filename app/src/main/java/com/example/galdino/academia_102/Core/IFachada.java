package com.example.galdino.academia_102.Core;

import com.example.galdino.academia_102.Core.Aplicacao.Resultado;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;

/**
 * Created by Galdino on 20/08/2015.
 */
public interface IFachada {
    public Resultado salvar(EntidadeDominio entidade);
    public Resultado alterar(EntidadeDominio entidade);
    public Resultado excluir(EntidadeDominio entidade);
    public Resultado consultar(EntidadeDominio entidade);
    public Resultado visualizar(EntidadeDominio entidade);
}
