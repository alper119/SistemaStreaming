package br.ufpb.dcx.lp20232;

// import java.io.IOException;
// import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        String cpf = "";

        while (cpf != "fim") {

            cpf = JOptionPane.showInputDialog("CPF:");

            if(cpf.equalsIgnoreCase("fim")){

                break;

            }
            // System.out.println(validadorCpf(cpf) ? "Válido":"Inválido");
            
        }
        
        // BancoDeDados obj_bd = new BancoDeDados("./bd.txt");

        // ArrayList<UsuarioStreaming> listaUsuarios = null;

        // try {

        //     listaUsuarios = obj_bd.carregaListaUsuarios();

        //     for(UsuarioStreaming u: listaUsuarios){

        //         System.out.println(u.toString());

        //     }
        
        // } catch(IOException e){

        //     System.err.println(e.getMessage());

        // } finally {

        //     System.out.println("final exec");
        // }

        // int loops = Integer.parseInt(JOptionPane.showInputDialog("Quantos cadastros?"));
        
        // ArrayList<UsuarioStreaming> listaUsuarios = new ArrayList<>();

        // for(int i = 0; i < loops; i++){

        //     String user = JOptionPane.showInputDialog("Nome do usuário:");
        //     String pass = JOptionPane.showInputDialog("Senha:");
        //     int age = Integer.parseInt(JOptionPane.showInputDialog("Idade:"));
        //     String cpf = JOptionPane.showInputDialog("CPF:");
        //     String card = JOptionPane.showInputDialog("Cartão de Crédito:");
        //     String plan = JOptionPane.showInputDialog("Plano:");

        //     listaUsuarios.add(new UsuarioStreaming(user, pass, age, cpf, card, plan));


        // }

        // try{

        //     obj_bd.salvaListaUsuarios(listaUsuarios);

        // } catch (IOException e){

        //     System.err.println(e.getMessage() + "\nMain.java");

        // }
        



    }
}