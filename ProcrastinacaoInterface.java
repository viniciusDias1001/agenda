package br.ufpb.projetos.procrastinacao;

import java.io.IOException;
import java.util.ArrayList;

public interface ProcrastinacaoInterface {
	ArrayList<Afazer> getAfazeres();

    ArrayList<Afazer> getAfazeresPendentes();

    ArrayList<Afazer> getAfazeresConcluidos();

    boolean marcarComoConcluido(String titulo);

    boolean cadastraAfazer(Afazer a);

    boolean removerAfazer(String titulo);
    
    void salvarDados() throws IOException;
    
    void recuperarDados() throws IOException;

}
