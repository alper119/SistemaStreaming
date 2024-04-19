package br.ufpb.dcx.lp20232;

public class CpfExistenteException extends Exception {

    public CpfExistenteException(String cpf){

        super(String.format("Cpf \"%s\" já existe no banco de dados.", cpf));

    }
    
}
