package br.com.bycoders.desafiodev.bankingservice.domains.records;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;

import java.util.List;

public record TransactionRecord(
        Owner owner,
        List<Transactions> transactions
) {

}