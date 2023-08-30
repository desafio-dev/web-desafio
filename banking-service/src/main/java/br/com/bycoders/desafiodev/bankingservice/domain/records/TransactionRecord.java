package br.com.bycoders.desafiodev.bankingservice.domain.records;

import br.com.bycoders.desafiodev.bankingservice.domain.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domain.entity.Transactions;

import java.util.List;

public record TransactionRecord(
        Owner owner,
        List<Transactions> transactions
) {

}