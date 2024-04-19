package br.ufpb.dcx.lp20232;

public class UsuarioExisteException extends Exception {

    public UsuarioExisteException(String nomeUsuario){

        super(String.format("Usuário \"%s\" já existe no banco de dados.", nomeUsuario));
        
    }
    
}
