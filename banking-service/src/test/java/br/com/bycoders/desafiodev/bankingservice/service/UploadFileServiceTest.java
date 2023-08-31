package br.com.bycoders.desafiodev.bankingservice.service;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import br.com.bycoders.desafiodev.bankingservice.repositories.OwnerRepository;
import br.com.bycoders.desafiodev.bankingservice.repositories.TransactionRepository;
import br.com.bycoders.desafiodev.bankingservice.services.UploadFileService;
import br.com.bycoders.desafiodev.bankingservice.services.impl.UploadFileServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@DirtiesContext
public class UploadFileServiceTest {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_insert_transactions_if_owner_does_not_exists_in_database() throws IOException {
        ClassPathResource arquivoCnab = new ClassPathResource("CNAB.txt");
        MultipartFile input = new MockMultipartFile("CNAB.txt", arquivoCnab.getInputStream());

        OwnerRepository mockOwnerRepository = Mockito.mock(OwnerRepository.class);
        TransactionRepository mockTransactionRepository = Mockito.mock(TransactionRepository.class);

        Owner owner = Owner.builder().id(1L).ownerName("salomao").storeName("devexpress").cpf("03594715280").build();

        Mockito.when(mockOwnerRepository.findByCpf(ArgumentMatchers.any(String.class))).thenReturn(owner);

        UploadFileService service = new UploadFileServiceImpl(mockOwnerRepository, mockTransactionRepository);
        Map<Owner, List<Transactions>> transactions = service.uploadFile(input);

        Assertions.assertNotNull(transactions);
        Mockito.verify(mockOwnerRepository, Mockito.times(0)).save(ArgumentMatchers.any());
        Mockito.verify(mockTransactionRepository, Mockito.atLeastOnce()).saveAll(ArgumentMatchers.any());
    }

    @Test
    public void should_insert_transactions_if_owner_already_exists_in_database() throws IOException {
        ClassPathResource arquivoCnab = new ClassPathResource("CNAB.txt");
        MultipartFile input = new MockMultipartFile("CNAB.txt", arquivoCnab.getInputStream());

        OwnerRepository mockOwnerRepository = Mockito.mock(OwnerRepository.class);
        TransactionRepository mockTransactionRepository = Mockito.mock(TransactionRepository.class);

        Mockito.when(mockOwnerRepository.findByCpf(ArgumentMatchers.any(String.class)))
                .thenReturn(null);

        UploadFileService service = new UploadFileServiceImpl(mockOwnerRepository, mockTransactionRepository);
        Map<Owner, List<Transactions>> transactions = service.uploadFile(input);

        Assertions.assertNotNull(transactions);
        Mockito.verify(mockOwnerRepository, Mockito.atLeastOnce()).save(ArgumentMatchers.any());
        Mockito.verify(mockTransactionRepository, Mockito.atLeastOnce()).saveAll(ArgumentMatchers.any());
    }

}
