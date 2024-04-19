package br.ufpb.dcx.lp20232;

public class MenorDeIdadeException extends Exception {

    public MenorDeIdadeException(String nomeUsuario){

        super(String.format("O usuário \"%s\" é menor de idade.", nomeUsuario));
        
    }
    
}
