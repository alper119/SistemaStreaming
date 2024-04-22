package br.ufpb.dcx.lp20232;

public class UsuarioStreaming {
    
    private String usuario;
    private String senha;
    private int idade;
    private String cpf;
    private String cartao;
    private String plano;

    public UsuarioStreaming(String usuario, String senha, int idade, String cpf, String cartao, String plano){

        this.usuario = usuario;
        this.senha = senha;
        this.idade = idade;
        this.cpf = cpf;
        this.cartao = cartao;
        this.plano = plano;

    }

    public UsuarioStreaming(String usuario, int idade, String cpf){

        this(usuario, "", idade, cpf, "", "");

    }

    public void setSenha(String novaSenha){

        this.senha = novaSenha;

    }

    public void setCartao(String novoCartao){

        this.cartao = novoCartao;

    }

    public void setPlano(String novoPlano){

        this.plano = novoPlano;

    }

    public String getUsuario(){ 
        
        return this.usuario;
    
    }

    public String getSenha(){ 
        
        return this.senha;
    
    }

    public int getIdade(){ 
        
        return this.idade;
    
    }

    public String getCpf(){ 
        
        return this.cpf;
    
    }

    public String getCartao(){ 
        
        return this.cartao;
    
    }

    public String getPlano(){ 
        
        return this.plano;
    
    }
    
    public String toString(){

        return String.format("UsuarioStreaming[usuario=\"%s\", idade=%s, cpf=\"%s\", plano=\"%s\"]", this.usuario, this.idade, this.cpf, this.plano);
        
    }
}
