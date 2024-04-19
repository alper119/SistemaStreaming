package br.ufpb.dcx.lp20232;

public class CartaoInvalidoException extends Exception {

    public CartaoInvalidoException(String cartao){

        super(String.format("O cartão \"%s\" não é válido.", cartao));
        
    }
    
}
