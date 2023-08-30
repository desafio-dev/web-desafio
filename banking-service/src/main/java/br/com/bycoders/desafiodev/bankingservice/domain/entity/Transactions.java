package br.com.bycoders.desafiodev.bankingservice.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

    private Long id;
    private String typeOperation;
    private String value;
    private ZonedDateTime date;
    private String cardNumber;
}
