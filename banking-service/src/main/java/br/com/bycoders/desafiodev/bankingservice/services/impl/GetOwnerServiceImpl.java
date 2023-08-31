package br.com.bycoders.desafiodev.bankingservice.services.impl;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import br.com.bycoders.desafiodev.bankingservice.repositories.OwnerRepository;
import br.com.bycoders.desafiodev.bankingservice.repositories.TransactionRepository;
import br.com.bycoders.desafiodev.bankingservice.services.GetOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetOwnerServiceImpl implements GetOwnerService {

    private final OwnerRepository ownerRepository;

    private final TransactionRepository transactionRepository;

    @Autowired
    public GetOwnerServiceImpl(OwnerRepository ownerRepository, TransactionRepository transactionRepository) {
        this.ownerRepository = ownerRepository;
        this.transactionRepository = transactionRepository;

    }

    @Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    @Override
    public List<Transactions> getTransactionsOwner(Long id) {
        return transactionRepository.findByOwnerId(id);
    }

    @Override
    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

    @Override
    public Optional<Transactions> getTransactionsOwnerById(Long id) {
        return transactionRepository.findById(id);
    }

}
