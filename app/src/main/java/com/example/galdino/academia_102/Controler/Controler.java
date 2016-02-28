package com.example.galdino.academia_102.Controler;

import com.example.galdino.academia_102.Core.Aplicacao.Resultado;
import com.example.galdino.academia_102.Core.Command.IComand;
import com.example.galdino.academia_102.Core.Command.Impl.AlterarComand;
import com.example.galdino.academia_102.Core.Command.Impl.ConsultarComand;
import com.example.galdino.academia_102.Core.Command.Impl.ExcluirComand;
import com.example.galdino.academia_102.Core.Command.Impl.SalvarComand;
import com.example.galdino.academia_102.Dominio.EntidadeDominio;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Galdino on 14/02/2016.
 */
public class Controler {
    private static final long serialVersionUID = 1L;
    public static final String DF_SALVAR = "salvar";
    public static final String DF_CONSULTAR = "consultar";
    public static final String DF_ALTERAR = "alterar";
    public static final String DF_EXCLUIR = "excluir";


    private static Map<String, IComand> commands;
    //private static Map<String, IViewHelper> vhs;
    public Controler()
    {
        commands = new HashMap<String, IComand>();
        // CADASTRA OS COMANDOS

        commands.put(DF_SALVAR,  new SalvarComand());
        commands.put(DF_EXCLUIR, new ExcluirComand());
        commands.put(DF_CONSULTAR, new ConsultarComand());
        commands.put(DF_ALTERAR, new AlterarComand());

    }
    public Resultado doPost(EntidadeDominio entidade, String operacao){
        IComand command = commands.get(operacao);
        Resultado resultado = command.execute(entidade); // Execulta a operação e
        return resultado; // retorna o resultado da mesma
    }
}
