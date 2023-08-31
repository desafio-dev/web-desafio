package br.com.bycoders.desafiodev.bankingservice.exceptions.custom;

public class OwnerNotFoundException extends RuntimeException {

    public OwnerNotFoundException(String message) {
        super(message);
    }

}
