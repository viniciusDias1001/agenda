package br.ufpb.projetos.procrastinacao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ProgramaProcrastinacao {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		
		ProcrastinacaoInterface listaAfazeres = new AfazerList("AfazerSimples.txt", "AfazerDiario.txt");
		
		try{
			listaAfazeres.recuperarDados();
			System.out.println(" Teus dados carregaram numa boa :)");
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog(null, " Os dados não carregaram. Algo Inesperado Ocorreu", "ERRO", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Explicação do Problema: " + e.getMessage());
		} catch (ArrayIndexOutOfBoundsException array){
            JOptionPane.showMessageDialog(null, array.getMessage());
        }
        ArrayList<Afazer> afazeresDiarios = new ArrayList<>();

		for (Afazer a: listaAfazeres.getAfazeres()){
            if (a.getAFAZER_TIPO().equals("Diário")){
                afazeresDiarios.add(a);
            }
        }
        for (Afazer ad: afazeresDiarios){
            if (!verificaAfazerDiario(ad)){
                ad.setTarefaConcluida(false);
            }
        }

		boolean sair = false;
		
		while(!sair) {
			
			String opcao = JOptionPane.showInputDialog(null, "Selecione a operação abaixo: "
			+ "\n[ 1 ] Cadastrar afazer;"
			+ "\n[ 2 ] Apagar afazer;"
			+ "\n[ 3 ] Ver tarefas concluidas;"
			+ "\n[ 4 ] Ver tarefas não concluidas;"
			+ "\n[ 5 ] Marcar tarefa como concluida;"
			+ "\n[ 6 ] Encerrar programa;", "Agenda de Afazeres", JOptionPane.QUESTION_MESSAGE);

			
			
			
			switch(opcao) {
			case "1":
				//cadastrar afazer;
                String escolha = JOptionPane.showInputDialog(null, "Cadastrar afazer simples(1)\n" +
				"Cadastrar afazer Diário(2)","Escolha do Tipo", JOptionPane.INFORMATION_MESSAGE);
                
                if (escolha.equals("1")) {
					String title = JOptionPane.showInputDialog(null, "Qual o título do afazer?","TITULO"
					,JOptionPane.INFORMATION_MESSAGE);
                    
                    Date data = new Date();
					String description = JOptionPane.showInputDialog(null, "Indique a descrição do afazer:"
					,"DESCRIÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    

                    listaAfazeres.cadastraAfazer(new AfazerSimples(title, data, description));

                } else if (escolha.equals("2")) {
					String title = JOptionPane.showInputDialog(null, "Qual o título do afazer?","TITULO"
					,JOptionPane.INFORMATION_MESSAGE);
                    Date data = new Date();
					String description = JOptionPane.showInputDialog(null, "Indique a descrição do afazer:"
					,"DESCRIÇÃO", JOptionPane.INFORMATION_MESSAGE);
                    listaAfazeres.cadastraAfazer(new AfazerDiario(title, data, description));
                }
                break;
			
			case "2":
				//apagar afazer;
                String titulo = JOptionPane.showInputDialog(null, "Digite o titulo do Afazer que deseja apagar","APAGAR TITULO"
					,JOptionPane.INFORMATION_MESSAGE);
                if (listaAfazeres.removerAfazer(titulo)){
                    JOptionPane.showMessageDialog(null, "Encontei e apaguei seu afazer", "CONCLUIDO", JOptionPane.OK_OPTION);
                } else{
                    JOptionPane.showMessageDialog(null, "Não encontramos esse afazer,Desculpe", "ERRO AO ENCONTRAR", JOptionPane.OK_OPTION);
                }
				break;
				
			case "3":
				// ver tarefas concluidas
				
				for(Afazer a : listaAfazeres.getAfazeresConcluidos()) {
					JOptionPane.showMessageDialog(null, a.getTitulo(), "AFAZERES CONCLUIDOS", JOptionPane.INFORMATION_MESSAGE);
				}
				
				break;
				
                case "4":
				for (Afazer a: listaAfazeres.getAfazeresPendentes()){
                    JOptionPane.showMessageDialog(null, a.getTitulo(), "AFAZERES PENDENTES", JOptionPane.INFORMATION_MESSAGE);
				}
				break;
				
			case "5":
                // marcar afazer como concluido
				String tituloConcluido = JOptionPane.showInputDialog(null, "Digite o titulo do afazer");
				
				if (listaAfazeres.marcarComoConcluido(tituloConcluido)){
                    JOptionPane.showMessageDialog(null, "Afazer foi encontrado e marcado como concluido", "MARCAÇÃO", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Não encontrei o seu Afazer", "MARCAÇÃO NÃO ENCONTRADA", JOptionPane.ERROR_MESSAGE);
                }

				break;
				
			case "6":
				// sair do programa
				try {
					listaAfazeres.salvarDados();
					JOptionPane.showMessageDialog(null, "Teus dados estão a salvo :)", "DADOS SALVOS", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(IOException e){
					JOptionPane.showMessageDialog(null, " Os dados não salvaram! Ocorreu um erro inesperado");
					JOptionPane.showMessageDialog(null, "Explicação do Erro:" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			      JOptionPane.showMessageDialog(null, "Muito obrigado por usar nosso programa ! :)");
				sair = true;
				break;
			
			default:
				JOptionPane.showMessageDialog(null, "Operação inexistente! Tente novamente!", "OPÇÃO INVALIDA", JOptionPane.ERROR_MESSAGE);
				break;
			}
			
		}
		sc.close();
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
