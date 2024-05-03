package br.ufpb.dcx.lp20232;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class Main {

    public static String inputComRetornoValido(String tituloJanela, String textoInput, String[] opcoes, int tamanhoInput, int icone, String msgErro, boolean aceitarNulo){

        boolean loopInput = true;

        String inputValue;

        do {

            JPanel painel = new JPanel();

            painel.setLayout(new GridLayout(2,2));

            painel.add(new JLabel(textoInput));
            JTextField campoTexto = new JTextField(tamanhoInput);
            painel.add(campoTexto);

            int qtdOpcoesRecebidas = opcoes.length;
            int tipoBotoes = 0;

            switch(qtdOpcoesRecebidas){

                case 1:
                    
                    tipoBotoes = JOptionPane.YES_OPTION;
                    break;

                case 2:

                    tipoBotoes = JOptionPane.YES_NO_OPTION;
                    break;

                default:

                    tipoBotoes = JOptionPane.YES_NO_OPTION;
                    break;

            }

            int opJanela = JOptionPane.showOptionDialog(null, painel, tituloJanela, tipoBotoes, icone, null, opcoes, opcoes[0]);
            inputValue = opJanela == JOptionPane.YES_OPTION ? campoTexto.getText():null;

            if(aceitarNulo){

                if(inputValue == null || inputValue.length() > 0){

                    loopInput = false;

                } else {

                    JOptionPane.showMessageDialog(null, msgErro, tituloJanela, JOptionPane.ERROR_MESSAGE);

                }

            } else {

                if(inputValue == null || inputValue.length() == 0){

                    JOptionPane.showMessageDialog(null, msgErro, tituloJanela, JOptionPane.ERROR_MESSAGE);
    
                } else {
                    
                    loopInput = false;
    
                } 

            }

        } while(loopInput);

        return inputValue;

    }

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

        while(opcao > 0){

            try {

                opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "Menu principal\n1 - Adicionar usuario\n2 - Remover usuário\n3 - Alterar senha do usuário\n4 - Alterar plano do usuário\n5 - Alterar cartão de crédito do usuário\n6 - Buscar usuário por nome\n7 - Buscar usuário por CPF\n0 - Sair\n\nDigite uma das opções acima", "Sistema de Gerenciamento de Contas de Streaming", JOptionPane.PLAIN_MESSAGE));
                
            } catch(NumberFormatException e){

                // Se o usuario clicar no botão "Cancelar" o programa é encerrado
                opcao = e.getMessage().equals("Cannot parse null string") ? 0:9;

            }

            String[] botoesBuscaUsuario = {"Buscar", "Voltar"};

            switch(opcao){

                case 1:

                    boolean loopCadastro = true;
                    
                    do {

                        // Coleta dos dados
                        String tituloJanela = "Adicionar usuário";

                        String[] botoesCampoUsuario = {"Avançar", "Cancelar"};
                        String usuario = inputComRetornoValido(tituloJanela, "Digite o usuário:", botoesCampoUsuario, 10, JOptionPane.QUESTION_MESSAGE, "O nome de usuário não pode ficar vazio", true);

                        // Caso o usuário clique na opção "Cancelar", o programa volta pro menu principal
                        if(usuario == null){ 

                            break;

                        }

                        String[] botoesOutrosCampos = {"Avançar"};

                        String senha =  inputComRetornoValido(tituloJanela, "Digite a senha:", botoesOutrosCampos, 10, JOptionPane.QUESTION_MESSAGE, "A senha não pode ficar vazia", false);

                        // Não sai do loop enquanto "idade" não for inteiro
                        int idade = 0;

                        do {

                            try {

                                idade = Integer.parseInt(inputComRetornoValido(tituloJanela, "Digite a idade:", botoesOutrosCampos, 10, JOptionPane.QUESTION_MESSAGE, "A idade não pode ficar vazia", false));

                            } catch(NumberFormatException e){

                                JOptionPane.showMessageDialog(null, "A idade deve ser um número", tituloJanela, JOptionPane.ERROR_MESSAGE);
                            
                            }

                        } while(idade <= 0);

                        String cpf = inputComRetornoValido(tituloJanela, "Digite um CPF válido:", botoesOutrosCampos, 14, JOptionPane.QUESTION_MESSAGE, "O CPF não pode ficar vazio", false).replaceAll("[^0-9]", "");

                        String cartao = inputComRetornoValido(tituloJanela, "Digite um cartão válido", botoesOutrosCampos, 19, JOptionPane.QUESTION_MESSAGE, "O número do cartão não pode ficar vazio", false).replaceAll("[^0-9]", "");

                        String plano = sistema.PLANOS[JOptionPane.showOptionDialog(null, "Qual o plano escolhido?", tituloJanela, 0, 3, null, sistema.PLANOS, sistema.PLANOS[0])];

                        // Tenta realizar o cadastro
                        try {

                            sistema.cadastrarUsuario(usuario, senha, idade, cpf, cartao, plano);
                            loopCadastro = false; // Se chegou nessa linha, não foi disparada nenhuma exceção; o cadastro foi realizado com sucesso.
                            JOptionPane.showMessageDialog(null, String.format("Usuário %s cadastrado com sucesso!", usuario), tituloJanela, JOptionPane.INFORMATION_MESSAGE);

                        } catch(UsuarioExisteException e){

                            JOptionPane.showMessageDialog(null, "O usuário já existe em nosso sistema", tituloJanela, JOptionPane.ERROR_MESSAGE);
                
                        } catch(MenorDeIdadeException e){

                            JOptionPane.showMessageDialog(null, "O usuário é menor de idade!", tituloJanela, JOptionPane.ERROR_MESSAGE);
                           
                        } catch(CartaoInvalidoException e){

                            JOptionPane.showMessageDialog(null, "O cartão digitado é invalido", tituloJanela, JOptionPane.ERROR_MESSAGE);
                           
                        } catch(CpfExistenteException e){

                            JOptionPane.showMessageDialog(null, "O CPF já existe em nosso sistema", tituloJanela, JOptionPane.ERROR_MESSAGE);
                          
                        } catch(CpfInvalidoException e){
                            
                            JOptionPane.showMessageDialog(null, "O CPF digitado é invalido!", tituloJanela, JOptionPane.ERROR_MESSAGE);
                           
                        } 
                        
                    } while(loopCadastro);
                        
                    break;

                case 2:

                    boolean cont = true;
                    
                    do {
                        
                        String[] opcoesInputUsuario = {"Remover", "Voltar"};
                        String tituloJanela = "Remover Usuário";

                        String user = inputComRetornoValido(tituloJanela, "Digite o usuário a ser removido:", opcoesInputUsuario, 10, JOptionPane.QUESTION_MESSAGE, "O nome de usuário não pode ficar vazio", true);
                        
                        if(user == null){

                            break;

                        }
                        
                        try {

                            sistema.removerUsuario(user);
                            cont = false;
                            JOptionPane.showMessageDialog(null, "Usuário removido com sucesso", tituloJanela, JOptionPane.INFORMATION_MESSAGE);

                        } catch(UsuarioInexistenteException e){

                            JOptionPane.showMessageDialog(null, "O usuário não existe no sistema!", tituloJanela, JOptionPane.ERROR_MESSAGE);
                           
                        }

                    } while(cont);

                    break;

                case 3:

                    boolean conti = true;
                    
                    do {

                        String[] opcoesInputUsuarioMudaSenha = {"Avançar", "Voltar"};
                        String[] opcoesInputMudaSenha = {"Avançar"};
                        String tituloJanelaMudaSenha = "Alterar senha do usuário";

                        String usuario = inputComRetornoValido(tituloJanelaMudaSenha, "Digite o usuário para mudar a senha:", opcoesInputUsuarioMudaSenha, 10, JOptionPane.QUESTION_MESSAGE, "Um usuário deve ser informado", true);

                        if(usuario == null){

                            break;

                        }

                        String senhaAntiga = inputComRetornoValido(tituloJanelaMudaSenha, "Digite a senha antiga:", opcoesInputMudaSenha, 10, JOptionPane.PLAIN_MESSAGE, "A senha antiga não pode estar vazia", false);
                        String senhaNova = inputComRetornoValido(tituloJanelaMudaSenha, "Digite a nova senha:", opcoesInputMudaSenha, 10, JOptionPane.PLAIN_MESSAGE, "A senha nova não pode estar vazia", false);

                        try {
                            
                            sistema.alterarSenhaDoUsuario(usuario, senhaAntiga, senhaNova);
                            conti = false;
                            JOptionPane.showMessageDialog(null, "Senha alterada com sucesso", tituloJanelaMudaSenha, JOptionPane.INFORMATION_MESSAGE);
                            
                        } catch(SenhaIncorretaException e){

                            JOptionPane.showMessageDialog(null, "A senha antiga está incorreta", tituloJanelaMudaSenha, JOptionPane.ERROR_MESSAGE);
                            
                        } catch(UsuarioInexistenteException e){

                            JOptionPane.showMessageDialog(null, "O usuário não existe", tituloJanelaMudaSenha, JOptionPane.ERROR_MESSAGE);
                            
                        }

                    } while(conti) ;
                        
                    break;

                case 4:
                    
                    boolean continuando = true;

                    do {
                       
                        String tituloJanelaMudaPlano = "Alterar plano do usuário";
                        String[] opcoesInputUsuarioMudaPlano = {"Avançar", "Voltar"};
                        String usuario = inputComRetornoValido(tituloJanelaMudaPlano, "Digite o usuário a ser alterado o plano:", opcoesInputUsuarioMudaPlano, 10, JOptionPane.QUESTION_MESSAGE, "O nome de usuário não pode ficar vazio", true);

                        if(usuario == null){

                            break;

                        }
                        
                        try{
                            
                            UsuarioStreaming usuarioAtual = sistema.buscarUsuarioPorNome(usuario);

                            String[] planosDisponiveis = new String[sistema.PLANOS.length-1];

                            // Retira o plano atual do usuário da lista de planos a ser oferecida
                            int i = 0;
                            for(String p: sistema.PLANOS){

                                if(!p.equals(usuarioAtual.getPlano())){

                                    planosDisponiveis[i] = p;
                                    i++;

                                }

                            }
                            
                            String planoNovo = planosDisponiveis[JOptionPane.showOptionDialog(null, "Qual o plano escolhido?", tituloJanelaMudaPlano, 0, 3, null, planosDisponiveis, planosDisponiveis[0])];
                            sistema.alterarPlano(usuario, planoNovo);
                            continuando = false;
                            JOptionPane.showMessageDialog(null, "Plano alterado com sucesso!", tituloJanelaMudaPlano, JOptionPane.INFORMATION_MESSAGE);

                        } catch(UsuarioInexistenteException e){

                            JOptionPane.showMessageDialog(null, "O usuário não existe", tituloJanelaMudaPlano, JOptionPane.ERROR_MESSAGE);
                            
                        }

                    } while(continuando);
                    
                    break;

                case 5:

                    boolean go = true;

                    do {

                        String tituloJanelaMudaCartao = "Alterar cartão de crédito do usuário";
                        String[] opcoesInputUsuarioMudaCartao = {"Avançar", "Voltar"};
                        String[] opcoesInputMudaCartao = {"Avançar"};

                        String usuario = inputComRetornoValido(tituloJanelaMudaCartao, "Digite o usuário para alterar o cartão:", opcoesInputUsuarioMudaCartao, 10, JOptionPane.QUESTION_MESSAGE, "É preciso informar um nome de usuário cadastrado", true);
                        
                        if(usuario == null){

                            break;

                        }

                        String cartaoNovo = inputComRetornoValido(tituloJanelaMudaCartao, "Digite o novo cartão", opcoesInputMudaCartao, 19, JOptionPane.QUESTION_MESSAGE, "É preciso informar um cartão de crédito", false).replaceAll("[^0-9]", "");

                        try {
                            
                            sistema.alterarCartao(usuario, cartaoNovo);
                            go = false;
                            JOptionPane.showMessageDialog(null, "Cartão alterado com sucesso!", tituloJanelaMudaCartao, JOptionPane.INFORMATION_MESSAGE);
                            
                        } catch(UsuarioInexistenteException e){
                            
                            JOptionPane.showMessageDialog(null, "O usuário não existe", tituloJanelaMudaCartao, JOptionPane.ERROR_MESSAGE);

                        } catch(CartaoInvalidoException e){

                            JOptionPane.showMessageDialog(null, "O cartão digitado é invalido!", tituloJanelaMudaCartao, JOptionPane.ERROR_MESSAGE);
                            
                        }

                    } while(go);
                    
                    break;

                case 6:

                    boolean going = true;
                    String tituloJanelaBuscaNome = "Buscar usuário por nome";

                    do {

                        String nome = inputComRetornoValido(tituloJanelaBuscaNome, "Digite o nome do usuário:", botoesBuscaUsuario, 10, JOptionPane.QUESTION_MESSAGE, "Não foi informado um nome de usuário", true);

                        if(nome == null){

                            break;
                            
                        }

                        try {    

                            UsuarioStreaming usuario = sistema.buscarUsuarioPorNome(nome);
                            JOptionPane.showMessageDialog(null, usuario.toString(), tituloJanelaBuscaNome, JOptionPane.PLAIN_MESSAGE);
                            going = false;

                        } catch(UsuarioInexistenteException e){

                            JOptionPane.showMessageDialog(null, "O usuário não existe", tituloJanelaBuscaNome, JOptionPane.ERROR_MESSAGE);

                        }

                    } while(going); 
                    
                    break;

                case 7:

                    boolean goes = true;
                    String tituloJanelaBuscaCpf = "Buscar usuário por CPF";

                    do {

                        String cpf = inputComRetornoValido(tituloJanelaBuscaCpf, "Digite o CPF a ser pesquisado", botoesBuscaUsuario, 10, JOptionPane.QUESTION_MESSAGE, "Não foi informado um CPF", true).replaceAll("[^0-9]", "");
                        
                        if(cpf == null){

                            break;

                        }

                        try {

                            UsuarioStreaming usuario = sistema.buscarUsuarioPorCpf(cpf);
                            JOptionPane.showMessageDialog(null, usuario.toString(), tituloJanelaBuscaCpf, JOptionPane.PLAIN_MESSAGE);
                            goes = false;

                        }catch(UsuarioInexistenteException e){

                            JOptionPane.showMessageDialog(null, "O usuário não existe", tituloJanelaBuscaCpf, JOptionPane.ERROR_MESSAGE);

                        }catch(CpfInvalidoException e){

                            JOptionPane.showMessageDialog(null, "O CPF digitado é invalido!", tituloJanelaBuscaCpf, JOptionPane.ERROR_MESSAGE);

                        }

                    } while(goes);
                    
                    break;

                case 0:

                    JOptionPane.showMessageDialog(null, "Obrigado por usar nosso sistema!", "Até logo", JOptionPane.PLAIN_MESSAGE);
                    break;
                
                default:
                    
                    JOptionPane.showMessageDialog(null, "Não foi informada uma opção válida", "Opção inválida", JOptionPane.ERROR_MESSAGE);
                    break;

            }

            
        }

    }
}