package br.ufpb.dcx.lp20232;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        /*
         * Menu
         * 1 - Adicionar usuario
         * 2 - Remover usuario
         * 3 - Alterar senha do usuario
         * 4 - Alterar plano do usuario
         * 5 - Alterar cartao do usuario
         * 6 - Buscar usuario por nome
         * 7 - Buscar usuario por CPF
         * 0 - Sair
         */

        int opcao = 9;

        while (opcao > 0) {

            try {

                opcao = Integer.parseInt(JOptionPane.showInputDialog("Sistema de Gerenciamento de Contas de Streaming\nDigite o número da opção e pressione ENTER:\n1 - Adicionar usuario\n2 - Remover usuario\n3 - Alterar senha do usuario\n4 - Alterar plano do usuario\n5 - Alterar cartao do usuario\n6 - Buscar usuario por nome\n7 - Buscar usuario por CPF\n0 - Sair"));
            
            } catch(NumberFormatException e){

                opcao = 9;

            }

            switch (opcao) {
                case 1:
                    
                    break;

                case 2:
                    
                    break;

                case 3:
                    
                    break;

                case 4:
                    
                    break;

                case 5:
                    
                    break;

                case 6:
                    
                    break;

                case 7:
                    
                    break;

                case 0:
                    
                    break;
                
                default:
                    break;
                    
            }

            
        }

    }
}