package com.example.galdino.academia_102.Core;



import com.example.galdino.academia_102.Dominio.EntidadeDominio;

import java.util.List;

/**
 * Created by Galdino on 20/08/2015.
 */
public interface IDAO {
    public EntidadeDominio salvar(EntidadeDominio entidade);
    public void alterar(EntidadeDominio entidade);
    public void excluir(EntidadeDominio entidade);
    public List<EntidadeDominio> consultar(EntidadeDominio entidade);
    public void criarColunas();
}
