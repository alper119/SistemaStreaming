package br.ufpb.dcx.lp20232;

// import java.io.IOException;
// import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {

    public static boolean validadorCpf(String cpf){

        boolean cpfValido = false;

        if(cpf.length() == 11){

            //Verifica se os digitos não são iguais
            if(!cpf.matches("(\\d)\\1{10}")){

                int somaDigitoVerificador1 = 0;
                int somaDigitoVerificador2 = 0;

                for(int i = 0; i < 9; i++){

                    somaDigitoVerificador1 += Character.getNumericValue(cpf.charAt(i)) * (10 - i);            

                }

                for(int i = 0; i < 10; i++){

                    somaDigitoVerificador2 += Character.getNumericValue(cpf.charAt(i)) * (11 - i);

                }

                int digitoVerificadorInformado1 = Character.getNumericValue(cpf.charAt(9));

                int digitoVerificadorGerado1 = (somaDigitoVerificador1*10)%11;

                if(digitoVerificadorGerado1 == 10){

                    digitoVerificadorGerado1 = 0;

                }

                int digitoVerificadorInformado2 = Character.getNumericValue(cpf.charAt(10));

                int digitoVerificadorGerado2 = (somaDigitoVerificador2*10)%11;

                if(digitoVerificadorGerado2 == 10){

                    digitoVerificadorGerado2 = 0;
                    
                }

                cpfValido = digitoVerificadorGerado1 == digitoVerificadorInformado1 && digitoVerificadorGerado2 == digitoVerificadorInformado2;
            }

        }

        return cpfValido;

    }
    public static void main(String[] args) {

        String cpf = "";

        while (cpf != "fim") {

            cpf = JOptionPane.showInputDialog("CPF:");

            if(cpf.equalsIgnoreCase("fim")){

                break;

            }
            System.out.println(validadorCpf(cpf) ? "Válido":"Inválido");
            
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