package br.ufpb.projetos.procrastinacao;

import java.util.Date;

public class AfazerSimples extends Afazer{

    private static final String TIPO_AFAZER = "Simples";
	
	public AfazerSimples() {
		super("", new Date(), "", false, null, TIPO_AFAZER);
	}

    public AfazerSimples(String titulo, Date data, String descricao){
        super(titulo, data, descricao, TIPO_AFAZER);
    }

	public AfazerSimples(String titulo, Date data, String descricao, boolean tarefaConcluida, Date dataDeConclusao) {
		super(titulo, data, descricao, tarefaConcluida, dataDeConclusao, TIPO_AFAZER);
	}

	public String getTipoAfazer(){
       return TIPO_AFAZER;
    }

}
