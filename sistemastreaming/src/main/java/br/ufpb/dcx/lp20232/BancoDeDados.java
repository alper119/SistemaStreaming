package br.ufpb.dcx.lp20232;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BancoDeDados {
    
    private String path;

    private final String SEPARADOR_DADOS = "#/#";

    public BancoDeDados(String path){

        this.path = path;

    }

    public ArrayList<UsuarioStreaming> carregaListaUsuarios() throws IOException{

        ArrayList<UsuarioStreaming> listaUsuarios = new ArrayList<>();

        BufferedReader leitor = new BufferedReader(new FileReader(this.path));

        String linha = null;

        do {

            linha = leitor.readLine();

            if(linha != null){

                String[] partes = linha.split(this.SEPARADOR_DADOS);

                listaUsuarios.add(new UsuarioStreaming(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], partes[4], partes[5]));

            }

        } while(linha != null);

        leitor.close();

        return listaUsuarios;

    }

    public void salvaListaUsuarios(ArrayList<UsuarioStreaming> listaUsuarios) throws IOException{

        // BufferedWriter escritor = null;

        // try{

            BufferedWriter escritor = new BufferedWriter(new FileWriter(this.path));

            for(UsuarioStreaming u: listaUsuarios){

                escritor.write(u.getUsuario() + this.SEPARADOR_DADOS + u.getSenha() + this.SEPARADOR_DADOS + u.getIdade() + this.SEPARADOR_DADOS + u.getCpf() + this.SEPARADOR_DADOS + u.getCartao() + this.SEPARADOR_DADOS + u.getPlano() + "\n");

            } 

        // } finally {

            // if(escritor != null){

                escritor.close();

            // }

        // }

    }
}
