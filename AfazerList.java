package br.ufpb.projetos.procrastinacao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class AfazerList implements ProcrastinacaoInterface {

    private ArrayList<Afazer> afazeres;
    private GravadorDeArquivos gravadorAfazerSimples;
    private GravadorDeArquivos gravadorAfazerDiario;

     public AfazerList (String arquivoTxtParaSalvarDadosAfazerSimples, String arquivoTxtParaSalvarDadosAfazerDiario){
        afazeres = new ArrayList<Afazer>();
        gravadorAfazerSimples = new GravadorDeArquivos(arquivoTxtParaSalvarDadosAfazerSimples);
        gravadorAfazerDiario = new GravadorDeArquivos(arquivoTxtParaSalvarDadosAfazerDiario);

    }

     @Override
     public ArrayList<Afazer> getAfazeresConcluidos() {
          ArrayList<Afazer> temporario = new ArrayList<Afazer>();
          for (Afazer a: afazeres){
              if (a.isTarefaConcluida()){
                  temporario.add(a);
              }
          }
         return temporario;
     }

    @Override
    public ArrayList<Afazer> getAfazeresPendentes() {
        ArrayList<Afazer> temporario = new ArrayList<Afazer>();
        for (Afazer a: afazeres){
            if (!a.isTarefaConcluida()){
                temporario.add(a);
            }
        }
        return temporario;
    }


    @Override
    public boolean marcarComoConcluido(String titulo) {
        for (Afazer a: afazeres){
            if (a.getTitulo().equals(titulo)) {
                a.setTarefaConcluida(true);
                a.setDataDeConclusao(new Date());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean cadastraAfazer(Afazer a) {
        return afazeres.add(a);
    }

    @Override
    public boolean removerAfazer(String titulo) {
         for (Afazer a: afazeres){
             if (a.getTitulo().equals(titulo)){
                 return afazeres.remove(a);
             }
         }
        return false;
    }

	@Override
	public void salvarDados() throws IOException{
		List<String> listaAfazerSimples = new ArrayList<String>();
        List<String> listaAfazerDiario = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		for(Afazer afazer : this.afazeres) {
            if (afazer.getAFAZER_TIPO().equals("Simples")) {
                if (afazer.getDataDeConclusao() != null) {
                    String addAfazer = afazer.getTitulo() + "#" + sdf.format(afazer.getDataDeCriacao()) + "#" + afazer.getDescricao() + "#"
                            + afazer.isTarefaConcluida() + "#" + sdf.format(afazer.getDataDeConclusao()) + "#" + afazer.getAFAZER_TIPO();
                    listaAfazerSimples.add(addAfazer);
                } else {
                    String addAfazer = afazer.getTitulo() + "#" + sdf.format(afazer.getDataDeCriacao()) + "#" + afazer.getDescricao() + "#"
                            + afazer.isTarefaConcluida() + "#" + afazer.getDataDeConclusao() + "#" + afazer.getAFAZER_TIPO();
                    listaAfazerSimples.add(addAfazer);
                }

            } else if (afazer.getAFAZER_TIPO().equals("Diário")) {
                if (afazer.getDataDeConclusao() != null) {
                    String addAfazer = afazer.getTitulo() + "#" + sdf.format(afazer.getDataDeCriacao()) + "#" + afazer.getDescricao() + "#"
                            + afazer.isTarefaConcluida() + "#" + sdf.format(afazer.getDataDeConclusao()) + "#" + afazer.getAFAZER_TIPO();
                    listaAfazerDiario.add(addAfazer);
                } else {
                    String addAfazer = afazer.getTitulo() + "#" + sdf.format(afazer.getDataDeCriacao()) + "#" + afazer.getDescricao() + "#"
                            + afazer.isTarefaConcluida() + "#" + afazer.getDataDeConclusao() + "#" + afazer.getAFAZER_TIPO();
                    listaAfazerDiario.add(addAfazer);
                }
            }
        }
		
		gravadorAfazerSimples.gravarTextoEmArquivo(listaAfazerSimples);
        gravadorAfazerDiario.gravarTextoEmArquivo(listaAfazerDiario);
	}

	@Override
	public void recuperarDados() throws IOException {
        List<String> stringRecuperadaAfazerSimples = gravadorAfazerSimples.recuperaDadosDeTexto();
        List<String> stringRecuperadaAfazerDiario = gravadorAfazerDiario.recuperaDadosDeTexto();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (String linha : stringRecuperadaAfazerSimples) {
            String[] arrayString = linha.split("#");
            try {
                if (arrayString[4].equals("null")) {
                    AfazerSimples afazerRecuperado = new AfazerSimples(arrayString[0], sdf.parse(arrayString[1]), arrayString[2]);
                    afazeres.add(afazerRecuperado);
                } else {
                    AfazerSimples afazerRecuperado = new AfazerSimples(arrayString[0], sdf.parse(arrayString[1]), arrayString[2], Boolean.parseBoolean(arrayString[3]), sdf.parse(arrayString[4]));
                    afazeres.add(afazerRecuperado);
                }
            } catch (ParseException abc) {
                System.out.println("Erro na recuperação dos dados: " + abc.getMessage());
            }
        }
        for (String linha : stringRecuperadaAfazerDiario) {
            String[] arrayString = linha.split("#");
            try {
                if (arrayString[4].equals("null")) {
                    AfazerDiario afazerRecuperado = new AfazerDiario(arrayString[0], sdf.parse(arrayString[1]), arrayString[2]);
                    afazeres.add(afazerRecuperado);
                } else {
                    AfazerDiario afazerRecuperado = new AfazerDiario(arrayString[0], sdf.parse(arrayString[1]), arrayString[2], Boolean.parseBoolean(arrayString[3]), sdf.parse(arrayString[4]));
                    afazeres.add(afazerRecuperado);
                }
            } catch (ParseException abc) {
                System.out.println("Erro na recuperação dos dados: " + abc.getMessage());
            }
        }
    }

	public ArrayList<Afazer> getAfazeres() {
		return afazeres;
	}


}
