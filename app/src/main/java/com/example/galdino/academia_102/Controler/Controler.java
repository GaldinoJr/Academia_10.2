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

//        vhs = new HashMap<String, IViewHelper>();
//        //CADASTRA AS CLASSES ************
//        vhs.put(Luz.class.getName(), new AguaViewHelper());
//        vhs.put(Agua.class.getName(), new LuzViewHelper());
//        vhs.put(Residencia.class.getName(), new ResidenciaViewHelper());
//        vhs.put(ConfiguracaoSistema.class.getName(), new ConfiguracaoSistemaViewHelper());
//        vhs.put(GastoAtual.class.getName(), new GastoAtualViewHelper());
//        vhs.put(GastoHoje.class.getName(), new GastoHojeViewHelper());
//        vhs.put(GastoMes.class.getName(), new GastoMesViewHelper());
//        vhs.put(GastoHora.class.getName(), new GastoHoraViewHelper());

    }
    public Resultado doPost(EntidadeDominio entidade, String operacao){
        // Pega qual a a operação desejada(EX: CRUD) e qual a classe que está execultando a operação
        //String operacao = (String)request.get("operacao");
       // String classe = (String)request.get("classe");
        // Através do nome da classe, diz qual é o viewHelper da correspondente da mesma
        //IViewHelper vh = vhs.get(classe); // manda o nome da classe e recebe o new do viewHelper Correspondente
       // EntidadeDominio entidade =  vh.getEntidade(request); // pega os dados da entidade correspondete
        // Verifica qual a a operação que está sendo feita
        // de acordo com o que está descrito acima, tem um mapa a chave é o nome da operação
        // e o conteudo é o new da mesma
        IComand command = commands.get(operacao);
        Resultado resultado = command.execute(entidade); // Execulta a operação e
        return resultado; // retorna o resultado da mesma
    }
}
