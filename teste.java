package br.ufpb.projetos.procrastinacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class teste {
    public static void main(String [] args){
        SimpleDateFormat z = new SimpleDateFormat("dd/MM/yyyy");
        AfazerDiario a = new AfazerDiario();
        a.setTitulo("Testar");
        a.setDescricao("Testar Método verificaFeitoHoje");
        a.setDataDeConclusao(new Date());
        a.setTarefaConcluida(true);
        System.out.println(a.sdf.format(a.getDataDeConclusao()));
        System.out.println(a.isTarefaConcluida());
        if (verificaAfazerDiario(a)){
            System.out.println("Feito hoje");
        } else {
            System.out.println("não fez hoje");
        }
        String b = z.format(new Date());
        Date x = new Date();
        System.out.println(x);
        b = z.format(x);
        System.out.println(b);
        try {
            Date c = z.parse(b);
            System.out.println(c);
        } catch (ParseException j){
            System.out.println(j);

        }
        System.out.println(a.getClass());
    }
    public static boolean verificaAfazerDiario(Afazer a) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (!a.isTarefaConcluida()) {
            return false;
        }else if (a.getDataDeConclusao() == null) {
            return false;
        }else if (sdf.format(a.getDataDeConclusao()).equals(sdf.format(new Date())) && a.isTarefaConcluida()) {
            return true;
        }
        return false;
    }
}
