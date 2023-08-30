package br.com.bycoders.desafiodev.bankingservice.factory;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;

public class OwnerFactory {

    public static Owner getOwnerDefault(String name, String cpf) {
        return Owner.builder()
                .ownerName(name)
                .cpf(cpf)
                .build();
    }
}
