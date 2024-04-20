package br.ufpb.dcx.lp20232;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        

        
        BancoDeDados obj_bd = new BancoDeDados("./bd.txt");

        ArrayList<UsuarioStreaming> listaUsuarios = null;

        try {

            listaUsuarios = obj_bd.carregaListaUsuarios();

            for(UsuarioStreaming u: listaUsuarios){

                System.out.println(u.toString());

            }
        
        } catch(IOException e){

            System.err.println(e.getMessage());

        } finally {

            System.out.println("final exec");
        }



    }
}