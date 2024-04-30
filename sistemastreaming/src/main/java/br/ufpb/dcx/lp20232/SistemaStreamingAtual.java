package br.ufpb.dcx.lp20232;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class SistemaStreamingAtual implements SistemaStreaming {
    

    private BancoDeDados objeto_bancoDeDados;
    private ArrayList<UsuarioStreaming> listaUsuarios;
    public final String[] PLANOS = {"Básico com anúncios", "Básico", "Premium"};

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
        
        boolean cartaoValido = false;

        if(cartao.length() == 16){

            int somaVerificacaoInformada = Character.getNumericValue(cartao.charAt(15));

            String parteAnalisada = cartao.substring(0, 15);

            boolean dobrarValor = true;

            int digitoAtual = 0;
            int dobroAtual = 0;

            int acumuladorDobro = 0;
            int acumuladorImpares = 0;

            for(int i = 14; i >= 0; i--){

                digitoAtual = Character.getNumericValue(parteAnalisada.charAt(i));

                if(dobrarValor){

                    dobroAtual = digitoAtual*2;

                    if(dobroAtual > 9){

                        int dezenas = dobroAtual%10;
                        int unidades = dobroAtual/10;

                        dobroAtual = dezenas + unidades;

                    }

                    acumuladorDobro += dobroAtual;

                    

                } else {

                    acumuladorImpares += digitoAtual;

                }

                dobrarValor = !dobrarValor; 

            }

            int somaVerificacaoCalculada = 10 - ((acumuladorDobro + acumuladorImpares)%10);

            cartaoValido = somaVerificacaoCalculada == somaVerificacaoInformada;
        }

        return cartaoValido;
        
    }

    private boolean usuarioExiste(String usuario){

        for(UsuarioStreaming u: this.listaUsuarios){

            if(u.getUsuario().equalsIgnoreCase(usuario)){

                return true;

            }

        }

        return false;

    }

    private void salvarBancoDeDados(){

        try {

            this.objeto_bancoDeDados.salvaListaUsuarios(this.listaUsuarios);

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Não foi possível salvar o arquivo do banco de dados", "Erro no banco de dados", JOptionPane.ERROR_MESSAGE);

        }

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
        this.salvarBancoDeDados();

    }

    @Override
    public void removerUsuario(String usuario) throws UsuarioInexistenteException {

        for(UsuarioStreaming u: this.listaUsuarios){

            if(u.getUsuario().equalsIgnoreCase(usuario)){

                this.listaUsuarios.remove(u);
                this.salvarBancoDeDados();
                return;

            }

        }

        throw new UsuarioInexistenteException(usuario);

    }

    @Override
    public void alterarSenhaDoUsuario(String usuario, String senhaAntiga, String senhaNova) throws UsuarioInexistenteException, SenhaIncorretaException {

        for(UsuarioStreaming u: this.listaUsuarios){

            if(u.getUsuario().equalsIgnoreCase(usuario)){

                if(u.getSenha().equals(senhaAntiga)){

                    u.setSenha(senhaNova);
                    this.salvarBancoDeDados();
                    return;

                } else {

                    throw new SenhaIncorretaException(usuario);

                }                

            }

        }
        
        throw new UsuarioInexistenteException(usuario);

    }

    @Override
    public void alterarPlano(String usuario, String planoNovo) throws UsuarioInexistenteException {

        for(UsuarioStreaming u: this.listaUsuarios){

            if(u.getUsuario().equalsIgnoreCase(usuario)){

                u.setPlano(planoNovo);
                this.salvarBancoDeDados();
                return;

            }

        }
        
        throw new UsuarioInexistenteException(usuario);

    }

    @Override
    public void alterarCartao(String usuario, String cartaoNovo) throws UsuarioInexistenteException, CartaoInvalidoException {

        for(UsuarioStreaming u: this.listaUsuarios){

            if(u.getUsuario().equalsIgnoreCase(usuario)){

                if(this.validadorCartao(cartaoNovo)){

                    u.setCartao(cartaoNovo);
                    this.salvarBancoDeDados();
                    return;

                } else {

                    throw new CartaoInvalidoException(cartaoNovo);

                }

            }
        
        }

        throw new UsuarioInexistenteException(usuario);

    }
    
    public UsuarioStreaming buscarUsuarioPorNome(String usuario) throws UsuarioInexistenteException {

        for(UsuarioStreaming u: this.listaUsuarios){

            if(u.getUsuario().equalsIgnoreCase(usuario)){

                return u;

            }
        }

        throw new UsuarioInexistenteException(usuario);

    }

    public UsuarioStreaming buscarUsuarioPorCpf(String cpf) throws UsuarioInexistenteException, CpfInvalidoException {

        if(this.validadorCpf(cpf)){

            for(UsuarioStreaming u: this.listaUsuarios){

                if(u.getCpf().equalsIgnoreCase(cpf)){

                    return u;

                }

            }

            throw new UsuarioInexistenteException(cpf);

        } else {

            throw new CpfInvalidoException(cpf);

        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((objeto_bancoDeDados == null) ? 0 : objeto_bancoDeDados.hashCode());
        result = prime * result + ((listaUsuarios == null) ? 0 : listaUsuarios.hashCode());
        result = prime * result + Arrays.hashCode(PLANOS);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SistemaStreamingAtual other = (SistemaStreamingAtual) obj;
        if (objeto_bancoDeDados == null) {
            if (other.objeto_bancoDeDados != null)
                return false;
        } else if (!objeto_bancoDeDados.equals(other.objeto_bancoDeDados))
            return false;
        if (listaUsuarios == null) {
            if (other.listaUsuarios != null)
                return false;
        } else if (!listaUsuarios.equals(other.listaUsuarios))
            return false;
        if (!Arrays.equals(PLANOS, other.PLANOS))
            return false;
        return true;
    }
    
}
