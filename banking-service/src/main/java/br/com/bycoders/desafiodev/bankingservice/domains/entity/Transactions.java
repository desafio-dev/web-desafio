package br.com.bycoders.desafiodev.bankingservice.domains.entity;


import jakarta.persistence.*;
import jakarta.transaction.UserTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSACTIONS")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TYPE_OPERATION")
    private String typeOperation;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "DATE")
    private ZonedDateTime date;

    @Column(name = "CARD_NUMBER")
    private String cardNumber;
}
