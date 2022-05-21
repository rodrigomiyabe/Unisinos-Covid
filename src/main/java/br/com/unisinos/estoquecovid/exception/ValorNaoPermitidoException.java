package br.com.unisinos.estoquecovid.exception;

public class ValorNaoPermitidoException extends Exception {

    private final int valorInformado;

    public ValorNaoPermitidoException(String mensagem, int valorInformado) {
        super(mensagem);
        this.valorInformado = valorInformado;
    }

}
