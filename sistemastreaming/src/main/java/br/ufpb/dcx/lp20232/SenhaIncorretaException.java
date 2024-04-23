package br.ufpb.dcx.lp20232;

public class SenhaIncorretaException extends Exception {

    public SenhaIncorretaException(String usuario){

        super(String.format("A senha informada para o usuário \"%s\" está incorreta.", usuario));
        
    }
    
}
