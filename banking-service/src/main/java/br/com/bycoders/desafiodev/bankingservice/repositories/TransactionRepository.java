package br.com.bycoders.desafiodev.bankingservice.repositories;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
}
