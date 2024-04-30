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
                    do{
                        try{
                            String usuario = JOptionPane.showInputDialog("Digite o usuário"); 
                            String senha = JOptionPane.showInputDialog("Digite a senha");
                            int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a idade:"));
                            String cpf = JOptionPane.showInputDialog("Digite um CPF válido:").replaceAll("[^0-9]", "");
                            String cartao = JOptionPane.showInputDialog("Digite um cartão válido").replaceAll("[^0-9]", "");
                            String plano = JOptionPane.showInputDialog("Qual o plano escolhido ---> (Básico com anúncios, básico e premium)");
                            sistema.cadastrarUsuario(usuario, senha, idade, cpf, cartao, plano);
                            continuar = false;

                        } catch(UsuarioExisteException e){
                            JOptionPane.showMessageDialog(null,"O usuário já existe em nosso sistema");
                
                        } catch(MenorDeIdadeException e){
                            JOptionPane.showMessageDialog(null,"O usuário é menor de idade!");
                           
                        } catch(CartaoInvalidoException e){
                            JOptionPane.showMessageDialog(null,"O cartão digitado é invalido");
                           
                        } catch(CpfExistenteException e){
                            JOptionPane.showMessageDialog(null,"O CPF já existe em nosso sistema");
                          
                        } catch(CpfInvalidoException e){
                            JOptionPane.showMessageDialog(null,"O CPF digitado é invalido!");
                           
                        } catch(NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "Digite uma idade válida");
                            
                        }

                        
                    }while (continuar);
                        
                    
                    
                    break;

                case 2:
                    boolean cont = true;
                   do{
                        try{
                            String user = JOptionPane.showInputDialog("Digite o usuário a ser removido:");
                            sistema.removerUsuario(user);
                            cont = false;
                            JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");;
                        } catch(UsuarioInexistenteException e){
                            JOptionPane.showMessageDialog(null,"O usuário não existe no sistema!");
                           
                        }
                    } while (cont); 
                        
                
                    
                    break;

                case 3:
                    boolean conti = true;
                    do{
                        try{
                            String usuario = JOptionPane.showInputDialog("Digite o usuário para mudar a senha:");
                            String senhaAntiga = JOptionPane.showInputDialog("Digite a senha antiga:");
                            String senhaNova = JOptionPane.showInputDialog("Digite a senha:");
                            sistema.alterarSenhaDoUsuario(usuario, senhaAntiga, senhaNova);
                            conti = false;
                            JOptionPane.showMessageDialog(null, "Senha alterada com sucesso");
                        } catch(SenhaIncorretaException e){
                            JOptionPane.showMessageDialog(null, "A senha antiga está incorreta");
                        } catch(UsuarioInexistenteException e){
                            JOptionPane.showMessageDialog(null, "O usuário não existe");
                        }

                    } while (conti) ;
                        
                    break;

                case 4:
                boolean continuando = true;
                    do{
                        try{
                            String usuario = JOptionPane.showInputDialog("Digite o usuário a ser alterado o plano:");
                            String planoNovo = JOptionPane.showInputDialog("Digite o plano escolhido: ---> (Básico com anúncios, básico e premium)");
                            sistema.alterarPlano(usuario, planoNovo);
                            continuando = false;
                            JOptionPane.showMessageDialog(null, "Plano alterado com sucesso!");

                        } catch(UsuarioInexistenteException e){
                            JOptionPane.showMessageDialog(null, " O usuário não existe");
                        }
                    }while(continuando);
                    
                    break;

                case 5:
                boolean go = true;
                do{
                    try{
                        String usuario = JOptionPane.showInputDialog("Digite o usuário para alterar o cartão:");
                        String cartaoNovo = JOptionPane.showInputDialog("Digite o novo cartão").replaceAll("[^0-9]", "");
                        sistema.alterarCartao(usuario, cartaoNovo);
                        go = false;
                        JOptionPane.showMessageDialog(null, "Cartão alterado com sucesso!");
                    } catch(UsuarioInexistenteException e){
                        JOptionPane.showMessageDialog(null, "O usuário não existe");
                    } catch(CartaoInvalidoException e){
                        JOptionPane.showMessageDialog(null, "O cartão digitado é invalido!");
                    }
                }while(go);
                    
                    break;

                case 6:
                boolean going = true;
                do{
                    try{
                        String nome = JOptionPane.showInputDialog("Digite o nome do usuário:");
                        UsuarioStreaming usuario= sistema.buscarUsuarioPorNome(nome);
                       JOptionPane.showMessageDialog(null, usuario.toString());
                        going = false;
                    } catch(UsuarioInexistenteException e){
                        JOptionPane.showMessageDialog(null, "O usuário não existe)");
                    }
                }while (going); 
                    
                    break;

                case 7:
                boolean goes = true;
                do{
                    try{
                    String cpf = JOptionPane.showInputDialog("Digite o CPF a ser pesquisado").replaceAll("[^0-9]", "");
                      UsuarioStreaming usuario = sistema.buscarUsuarioPorCpf(cpf);
                      JOptionPane.showMessageDialog(null, usuario.toString());
                      goes = false;
                    }catch(UsuarioInexistenteException e){
                        JOptionPane.showMessageDialog(null, "O usuário não existe");
                    }catch(CpfInvalidoException e){
                        JOptionPane.showMessageDialog(null, "O CPF digitado é invalido!");
                    }
                }while(goes);
                    
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