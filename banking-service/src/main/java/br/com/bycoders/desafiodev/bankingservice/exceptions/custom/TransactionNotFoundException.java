package br.com.bycoders.desafiodev.bankingservice.exceptions.custom;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(String message) {
        super(message);
    }

}
