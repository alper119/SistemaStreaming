package br.ufpb.dcx.lp20232;

import java.io.IOException;
import java.util.ArrayList;

public class SistemaStreamingAtual implements SistemaStreaming {

    private BancoDeDados objeto_bancoDeDados;
    private ArrayList<UsuarioStreaming> listaUsuarios;
    public static final String[] PLANOS = {"básico com anúncios", "básico", "premium"};

    public SistemaStreamingAtual(String pathBancoDeDados){

        this.objeto_bancoDeDados = new BancoDeDados(pathBancoDeDados);

        try{

            this.listaUsuarios = this.objeto_bancoDeDados.carregaListaUsuarios();

        } catch(IOException e){

            System.err.println(e.getMessage() + "\nSistemaStreamingAtual()@SistemaStreamingAtual.java");

            //Presume-se erro na leitura do arquivo infomado no parâmetro pathBancoDeDados
            this.listaUsuarios = new ArrayList<UsuarioStreaming>();

        }

    }

    private boolean validadorCpf(String cpf){

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

    private boolean validadorCartao(String cartao){

        return cartao.length() > 5;
        
    }

    private boolean usuarioExiste(String usuario){

        for(UsuarioStreaming u: this.listaUsuarios){

            if(u.getUsuario().equalsIgnoreCase(usuario)){

                return true;

            }

        }

        return false;

    }

    @Override
    public void cadastrarUsuario(String usuario, String senha, int idade, String cpf, String cartao, String plano) throws UsuarioExisteException, CartaoInvalidoException, CpfExistenteException, CpfInvalidoException, MenorDeIdadeException {
        
        // Validação de dados
        if(this.usuarioExiste(usuario)){

            throw new UsuarioExisteException(usuario);

        }

        if(idade < 18){

            throw new MenorDeIdadeException(usuario);

        }

        if(!this.validadorCpf(cpf)){

            throw new CpfInvalidoException(cpf);

        } else {

            for(UsuarioStreaming u: this.listaUsuarios){

                if(u.getCpf().equals(cpf)){

                    throw new CpfExistenteException(cpf);
                    
                }

            }
        }

        if(!this.validadorCartao(cartao)){

            throw new CartaoInvalidoException(cartao);

        }

        listaUsuarios.add(new UsuarioStreaming(usuario, senha, idade, cpf, cartao, plano));

    }

    @Override
    public void removerUsuario(String usuario) throws UsuarioInexistenteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removerUsuario'");
    }

    @Override
    public void alterarSenhaDoUsuario(String usuario, String senhaAntiga, String senhaNova)
            throws UsuarioInexistenteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alterarSenhaDoUsuario'");
    }

    @Override
    public void alterarPlano(String usuario, String planoNovo) throws UsuarioInexistenteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alterarPlano'");
    }

    @Override
    public void alterarCartao(String usuario, String cartaoNovo)
            throws UsuarioInexistenteException, CartaoInvalidoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alterarCartao'");
    }
    
}
