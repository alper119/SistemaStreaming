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
        SistemaStreamingAtual sistema = new SistemaStreamingAtual("./bd.txt");
        int opcao = 9;

        while (opcao > 0) {

            try {

                opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "Menu principal\n1 - Adicionar usuario\n2 - Remover usuário\n3 - Alterar senha do usuário\n4 - Alterar plano do usuário\n5 - Alterar cartão de crédito do usuário\n6 - Buscar usuário por nome\n7 - Buscar usuário por CPF\n0 - Sair\n\nDigite uma das opções acima", "Sistema de Gerenciamento de Contas de Streaming", JOptionPane.QUESTION_MESSAGE));
            
            } catch(NumberFormatException e){

                opcao = 9;
                System.out.println(e.getMessage());

            }

            switch (opcao) {

                case 1:
                    boolean continuar = true;
                    while(continuar){
                        try{
                            String usuario = JOptionPane.showInputDialog("Digite o usuário"); 
                            String senha = JOptionPane.showInputDialog("Digite a senha");
                            int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a idade:"));
                            String cpf = JOptionPane.showInputDialog("Digite um CPF válido:");
                            String cartao = JOptionPane.showInputDialog("Digite um cartão válido");
                            String plano = JOptionPane.showInputDialog("Qual o plano escolhido ---> (Básico com anúncios, básico e premium)");
                            sistema.cadastrarUsuario(usuario, senha, idade, cpf, cartao, plano);
                            continuar = false;

                        } catch(UsuarioExisteException e){
                            JOptionPane.showMessageDialog(null,"O usuário já existe em nosso sistema");
                            continuar = true;
                        } catch(MenorDeIdadeException e){
                            JOptionPane.showMessageDialog(null,"O usuário é menor de idade!");
                            continuar = true;
                        } catch(CartaoInvalidoException e){
                            JOptionPane.showMessageDialog(null,"O cartão digitado é invalido");
                            continuar = true;
                        } catch(CpfExistenteException e){
                            JOptionPane.showMessageDialog(null,"O CPF já existe em nosso sistema");
                            continuar = true;
                        } catch(CpfInvalidoException e){
                            JOptionPane.showMessageDialog(null,"O CPF digitado é invalido!");
                            continuar = true;
                        } catch(NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "Digite uma idade válida");
                            continuar = true;
                        }

                        
                    }
                    
                    break;

                case 2:
                    boolean cont = true;
                    while(cont){
                        try{
                            String user = JOptionPane.showInputDialog("Digite o usuário a ser removido:");
                            sistema.removerUsuario(user);
                            cont = false;
                        } catch(UsuarioInexistenteException e){
                            JOptionPane.showMessageDialog(null,"O usuário não existe no sistema!");
                            cont = true;
                        }
                    }
                    
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
                    
                    JOptionPane.showMessageDialog(null, "Não foi informada uma opção válida", "Opção inválida", JOptionPane.ERROR_MESSAGE);
                    break;

            }

            
        }

    }
}