package br.ufpb.projetos.procrastinacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class GravadorDeArquivos {
	
	private String nomeArquivo1;
	public GravadorDeArquivos(String nomeArquivo1) {
		this.nomeArquivo1 = nomeArquivo1;
	}
	
	public List<String> recuperaDadosDeTexto() throws IOException{
		List<String> listaDadosRecuperados = new ArrayList<>();
		BufferedReader leitor = null;
		
		try {
			leitor = new BufferedReader(new FileReader(this.nomeArquivo1));
			String linhaLida = null;
			do {
				linhaLida = leitor.readLine();
				if(linhaLida != null) {
					listaDadosRecuperados.add(linhaLida);
				}
			}while(linhaLida != null);
		}
		finally {
			if(leitor != null) {
				leitor.close();
			}
		}
		return listaDadosRecuperados;
	}

	public void gravarTextoEmArquivo(List<String> listaGravar) throws IOException{
		
		BufferedWriter gravador = null;
		try {
			gravador = new BufferedWriter(new FileWriter(this.nomeArquivo1));
			
			for(String stringGravar: listaGravar) {
				gravador.write(stringGravar + "\n");
			}
			
		}
		finally {
			if(gravador != null) {
				gravador.close();
			}
		}
		
		
		
	}


	
	
	
	
	
	
	
	
}
