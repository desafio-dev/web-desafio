package br.com.bycoders.desafiodev.bankingservice.domains.records;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;

import java.time.ZonedDateTime;
import java.util.List;

public record ErrorResponse(
       String message,
       int errorCode,
       ZonedDateTime date
) {

}