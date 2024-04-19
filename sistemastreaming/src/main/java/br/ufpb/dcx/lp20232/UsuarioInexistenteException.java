package br.ufpb.dcx.lp20232;

public class UsuarioInexistenteException extends Exception {

    public UsuarioInexistenteException(String usuario){

        super(String.format("O usuário \"%s\" já existe no banco de dados.", usuario));
        
    }
    
}
