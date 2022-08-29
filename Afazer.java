package br.ufpb.projetos.procrastinacao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class Afazer {
	
    private String titulo;
    private Date dataDeCriacao;
    private String descricao;
    private boolean tarefaConcluida;
    private Date dataDeConclusao;
    private final String AFAZER_TIPO;
    public final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Afazer(String titulo, Date dataDeCriacao, String descricao, String AFAZER_TIPO){
        this.dataDeConclusao = null;
        setTitulo(titulo);
        this.tarefaConcluida = false;
        setDataDeCriacao(dataDeCriacao);
        setDescricao(descricao);
        this.AFAZER_TIPO = AFAZER_TIPO;
    }

    public Afazer(String titulo, Date dataDeCriacao, String descricao, boolean tarefaConcluida, Date dataDeConclusao, String AFAZER_TIPO){
        this.titulo = titulo;
        this.dataDeCriacao = dataDeCriacao;
        this.descricao = descricao;
        this.tarefaConcluida = tarefaConcluida;
        this.dataDeConclusao = dataDeConclusao;
        this.AFAZER_TIPO = AFAZER_TIPO;
    }

    public Afazer(){
        this("Sem Titulo", new  Date(), "Sem descrição", false, null, "Simples");
    }
    
    @Override
    public String toString(){
        if (isTarefaConcluida()) {
            String s = String.format(
                    "Titulo: %s" +
                    "Data de criação: %s"+
                    "Descrição: %s"+
                    "Data de conclusão: %s", this.titulo, sdf.format(this.dataDeCriacao), this.descricao, sdf.format(this.dataDeConclusao));
            return s;
        } else{
            String s = String.format(
                    "Titulo: %s"+
                    "Data de criação: %s"+
                    "Descrição: %s"+
                    "Data de conclusão: Ainda não foi concluída", this.titulo, sdf.format(this.dataDeCriacao), this.descricao);
            return s;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Afazer)) return false;
        Afazer afazer = (Afazer) o;
        return Objects.equals(titulo, afazer.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date data) {
        this.dataDeCriacao = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getAFAZER_TIPO(){
        return this.AFAZER_TIPO;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isTarefaConcluida() {
        return tarefaConcluida;
    }

    public void setTarefaConcluida(boolean tarefaConcluida) {
        this.tarefaConcluida = tarefaConcluida;
    }

	public Date getDataDeConclusao() {
		return dataDeConclusao;
	}

	public void setDataDeConclusao(Date dataDeConclusao) {
		this.dataDeConclusao = dataDeConclusao;
	}

}
