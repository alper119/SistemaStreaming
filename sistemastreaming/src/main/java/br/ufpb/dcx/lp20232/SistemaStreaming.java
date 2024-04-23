package br.ufpb.dcx.lp20232;

public interface SistemaStreaming {
    
    public void cadastrarUsuario(String usuario, String senha, int idade, String cpf, String cartao, String plano) throws UsuarioExisteException, CartaoInvalidoException, CpfExistenteException, CpfInvalidoException, MenorDeIdadeException;

    public void removerUsuario(String usuario) throws UsuarioInexistenteException;

    public void alterarSenhaDoUsuario(String usuario, String senhaAntiga, String senhaNova) throws UsuarioInexistenteException, SenhaIncorretaException;

    public void alterarPlano(String usuario, String planoNovo) throws UsuarioInexistenteException;

    public void alterarCartao(String usuario, String cartaoNovo) throws UsuarioInexistenteException, CartaoInvalidoException;

}
