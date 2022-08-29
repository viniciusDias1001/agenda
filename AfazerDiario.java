package br.ufpb.projetos.procrastinacao;

import java.util.Date;

public class AfazerDiario extends Afazer {

    private static final String TIPO_AFAZER = "Diário";

    public AfazerDiario(String titulo, Date data, String descricao){
        super(titulo, data, descricao, TIPO_AFAZER);
        setTarefaConcluida(false);
    }

    public AfazerDiario(String titulo, Date data, String descricao, boolean tarefaconcluida, Date dataDeConclusao){
        super(titulo, data, descricao, tarefaconcluida, dataDeConclusao, TIPO_AFAZER);
    }

    public AfazerDiario() {
        super("Sem titulo", new Date(), "Sem Descrição", TIPO_AFAZER);
        setTarefaConcluida(false);
    }

    public boolean verificaAfazerDiario() {
        if (!isTarefaConcluida()) {
            return false;
        }else if (getDataDeConclusao() == null) {
            return false;
        }else if (sdf.format(getDataDeConclusao()).equals(super.sdf.format(new Date())) && isTarefaConcluida()) {
            return true;
        }
        return false;
    }

    public String getTipoAfazer(){
        return TIPO_AFAZER;
    }
}
