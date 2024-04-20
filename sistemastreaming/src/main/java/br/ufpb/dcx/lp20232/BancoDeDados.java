package br.ufpb.dcx.lp20232;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BancoDeDados {
    
    private String path;

    private final String SEPARADOR_DADOS = "#/#";

    public BancoDeDados(String path){

        this.path = path;

    }

    public ArrayList<UsuarioStreaming> carregaListaUsuarios() throws IOException{

        BufferedReader leitor = null;

        String linha = null;

        ArrayList<String> fileContents = new ArrayList<>();

        ArrayList<UsuarioStreaming> listaUsuarios = new ArrayList<>();

        try {

            leitor = new BufferedReader(new FileReader(this.path));
            

            do {

                linha = leitor.readLine();

                if(linha != null){

                    fileContents.add(linha);
                }

            } while(linha != null);


        } catch(IOException e){

            System.err.println(e.getMessage());

        } finally {

            if(leitor != null) leitor.close();

        }

        for(String l: fileContents){

            String[] partes = l.split(this.SEPARADOR_DADOS);

            // String usuario, String senha, int idade, String cpf, String cartao, String plano

            listaUsuarios.add(new UsuarioStreaming(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], partes[4], partes[5]));

        }

        return listaUsuarios;

    }
}
