package br.com.bycoders.desafiodev.bankingservice.repositories;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findByCpf(String cpf);

}
