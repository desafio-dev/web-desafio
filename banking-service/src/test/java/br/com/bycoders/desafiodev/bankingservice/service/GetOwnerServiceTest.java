package br.com.bycoders.desafiodev.bankingservice.service;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import br.com.bycoders.desafiodev.bankingservice.domains.enums.TypeOperationEnum;
import br.com.bycoders.desafiodev.bankingservice.repositories.OwnerRepository;
import br.com.bycoders.desafiodev.bankingservice.repositories.TransactionRepository;
import br.com.bycoders.desafiodev.bankingservice.services.GetOwnerService;
import br.com.bycoders.desafiodev.bankingservice.services.impl.GetOwnerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public class GetOwnerServiceTest {

    @Test
    public void should_return_a_list_of_owner() {
        OwnerRepository mockOwnerRepository = Mockito.mock(OwnerRepository.class);
        TransactionRepository mockTransactionRepository = Mockito.mock(TransactionRepository.class);
        Owner owner = getDefaultOwner();
        Mockito.when( mockOwnerRepository.findAll() ).thenReturn(List.of(owner));

        GetOwnerService getOwnerService = new GetOwnerServiceImpl(mockOwnerRepository, mockTransactionRepository);
        List<Owner> owners = getOwnerService.getAll();

        Assertions.assertNotNull(owners);
        Mockito.verify(mockOwnerRepository, Mockito.atLeastOnce()).findAll();

    }

    @Test
    public void should_return_a_owner_by_id() {
        OwnerRepository mockOwnerRepository = Mockito.mock(OwnerRepository.class);
        TransactionRepository mockTransactionRepository = Mockito.mock(TransactionRepository.class);
        Owner owner = getDefaultOwner();
        Mockito.when( mockOwnerRepository.findById(ArgumentMatchers.any(Long.class)) ).thenReturn(Optional.ofNullable(owner));

        GetOwnerService getOwnerService = new GetOwnerServiceImpl(mockOwnerRepository, mockTransactionRepository);
        Optional<Owner> ownerById = getOwnerService.getOwnerById(2L);

        Assertions.assertNotNull(ownerById);
        Mockito.verify(mockOwnerRepository, Mockito.atLeastOnce()).findById(ArgumentMatchers.any(Long.class));

    }

    @Test
    public void should_return_a_list_of_transaction_by_owner() {
        OwnerRepository mockOwnerRepository = Mockito.mock(OwnerRepository.class);
        TransactionRepository mockTransactionRepository = Mockito.mock(TransactionRepository.class);

        Mockito.when( mockTransactionRepository.findByOwnerId(ArgumentMatchers.any(Long.class))).thenReturn(List.of(
                Transactions.builder()
                        .id(1L)
                        .date(ZonedDateTime.now())
                        .typeOperation("5")
                        .value(BigDecimal.valueOf(100))
                        .cardNumber("4753****3153")
                        .owner(Owner.builder()
                                .id(2L)
                                .cpf("11111111111")
                                .storeName("devexpress store")
                                .ownerName("salomao").build())
                        .build()));

        GetOwnerService getOwnerService = new GetOwnerServiceImpl(mockOwnerRepository, mockTransactionRepository);
        List<Transactions> owners = getOwnerService.getTransactionsOwner(2L);

        Assertions.assertNotNull(owners);
        Mockito.verify(mockTransactionRepository, Mockito.atLeastOnce()).findByOwnerId(2L);

    }

    @Test
    public void should_return_a_transaction_by_id() {
        OwnerRepository mockOwnerRepository = Mockito.mock(OwnerRepository.class);
        TransactionRepository mockTransactionRepository = Mockito.mock(TransactionRepository.class);

        Mockito.when( mockTransactionRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(
                Optional.ofNullable(Transactions.builder()
                        .id(1L)
                                .descriptionOperation(
                                        TypeOperationEnum.getOperationByTypeOperation(Integer.parseInt("5")).getDescription()
                                )
                        .date(ZonedDateTime.now())
                        .typeOperation("5")
                        .value(BigDecimal.valueOf(100))
                        .cardNumber("4753****3153")
                        .owner(Owner.builder()
                                .id(2L)
                                .cpf("11111111111")
                                .storeName("devexpress store")
                                .ownerName("salomao").build())
                        .build()));

        GetOwnerService getOwnerService = new GetOwnerServiceImpl(mockOwnerRepository, mockTransactionRepository);
        Optional<Transactions> owners = getOwnerService.getTransactionsOwnerById(1L);

        Assertions.assertNotNull(owners);
        Mockito.verify(mockTransactionRepository, Mockito.atLeastOnce()).findById(1L);

        Assertions.assertTrue(owners.isPresent());
        Assertions.assertNotNull(owners.get());
        Transactions transactions = owners.get();
        Assertions.assertNotNull(transactions.getDescriptionOperation());
        Assertions.assertEquals(transactions.getDescriptionOperation(), TypeOperationEnum.REEBIMENTO_EMPRESTIMO.getDescription());

    }

    private Owner getDefaultOwner() {
        return Owner.builder()
                .cpf("11111111111")
                .storeName("devexpress store")
                .ownerName("salomao")
                .transactionsList(List.of(Transactions.builder().id(1L).date(ZonedDateTime.now()).typeOperation("5").value(BigDecimal.valueOf(100)).cardNumber("4753****3153").build()))
                .build();
    }


}
