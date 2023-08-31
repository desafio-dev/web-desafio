package br.com.bycoders.desafiodev.bankingservice.domains.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OWNER")
public class Owner extends RepresentationModel<Owner> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "OWNER_NAME")
    private String ownerName;

    @Column(nullable = false, name = "CPF", unique = true)
    private String cpf;

    @Column(nullable = false, name = "STORE_NAME")
    private String storeName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_list_id", referencedColumnName = "id")
    private List<Transactions> transactionsList = new ArrayList<>();
}
