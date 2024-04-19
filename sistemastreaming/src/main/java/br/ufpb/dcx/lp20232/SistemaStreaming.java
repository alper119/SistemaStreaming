package br.ufpb.dcx.lp20232;

public interface SistemaStreaming {
    
    public void cadastrarUsuario(String usuario, String senha, int idade, String cpf, String cartao, String plano) throws UsuarioExisteException, CartaoInvalidoException, CpfExistenteException, CpfInvalidoException;

    public void removerUsuario(String usuario) throws UsuarioInexistenteException;

    public void alterarSenhaDoUsuario(String senhaAntiga, String senhaNova) throws UsuarioInexistenteException;

    public void alterarPlano(String planoNovo) throws UsuarioInexistenteException;

    public void alterarCartao(String cartaoNovo) throws UsuarioInexistenteException, CartaoInvalidoException;

}

/*
 * UsuarioExisteException
 * CpfExistenteException
 * MenorDeIdadeException
 * CpfInvalidoException
 * CartaoInvalidoException
 * UsuarioInexistenteException
 */