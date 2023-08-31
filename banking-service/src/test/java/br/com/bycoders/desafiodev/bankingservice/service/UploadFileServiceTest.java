package br.com.bycoders.desafiodev.bankingservice.service;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import br.com.bycoders.desafiodev.bankingservice.repositories.OwnerRepository;
import br.com.bycoders.desafiodev.bankingservice.repositories.TransactionRepository;
import br.com.bycoders.desafiodev.bankingservice.services.UploadFileService;
import br.com.bycoders.desafiodev.bankingservice.services.impl.UploadFileServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@DirtiesContext
public class UploadFileServiceTest {

    @InjectMocks
    private static UploadFileServiceImpl uploadFileService;

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

        List<Transactions> outputTransactions = new ArrayList<>();
        Mockito.when(mockTransactionRepository.saveAll(ArgumentMatchers.any())).thenReturn(outputTransactions);
        Mockito.when(mockOwnerRepository.findByCpf(ArgumentMatchers.any())).thenReturn(null);
        Object owner = Mockito.mock(Owner.class);
        Mockito.when(mockOwnerRepository.save(ArgumentMatchers.any())).thenReturn(owner);

        UploadFileService service = new UploadFileServiceImpl(mockOwnerRepository, mockTransactionRepository);

        Map<Owner, List<Transactions>> transactions = service.uploadFile(input);

        Assertions.assertNotNull(transactions);
        Mockito.verify(mockTransactionRepository, Mockito.atLeastOnce()).saveAll(ArgumentMatchers.any());
    }

    @Test
    public void should_insert_transactions_if_owner_already_exists_in_database() throws IOException {
        ClassPathResource arquivoCnab = new ClassPathResource("CNAB.txt");
        MultipartFile input = new MockMultipartFile("CNAB.txt", arquivoCnab.getInputStream());
        OwnerRepository mockOwnerRepository = Mockito.mock(OwnerRepository.class);
        TransactionRepository mockTransactionRepository = Mockito.mock(TransactionRepository.class);

        List<Transactions> outputTransactions = new ArrayList<>();
        Mockito.when(mockTransactionRepository.saveAll(ArgumentMatchers.any())).thenReturn(outputTransactions);
        Owner ownerOuput = Mockito.mock(Owner.class);
        Mockito.when(ownerOuput.getCpf()).thenReturn("11111111111");
        Mockito.when(mockOwnerRepository.findByCpf(ArgumentMatchers.any())).thenReturn(ownerOuput);
        Object owner = Mockito.mock(Owner.class);
        Mockito.when(mockOwnerRepository.save(ArgumentMatchers.any())).thenReturn(owner);

        UploadFileService service = new UploadFileServiceImpl(mockOwnerRepository, mockTransactionRepository);

        Map<Owner, List<Transactions>> transactions = service.uploadFile(input);

        Assertions.assertNotNull(transactions);
        Mockito.verify(mockTransactionRepository, Mockito.atLeastOnce()).saveAll(ArgumentMatchers.any());
    }

}
