package br.ufpb.dcx.lp20232;

public class CpfInvalidoException extends Exception {

    public CpfInvalidoException(String cpf){

        super(String.format("O cpf \"%s\" não é válido.", cpf));
        
    }
    
}
