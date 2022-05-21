package br.com.unisinos.estoquecovid.exception;

public class SaldoNegativoException extends Exception {

    private final int saldoAtual;

    public SaldoNegativoException(String mensagem, int saldoAtual) {
        super(mensagem);
        this.saldoAtual = saldoAtual;
    }

    public int getSaldoAtual() {
        return saldoAtual;
    }
}
