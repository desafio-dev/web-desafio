package br.com.bycoders.desafiodev.bankingservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Owner {

    private Long id;
    private String ownerName;
    private String cpf;

}
