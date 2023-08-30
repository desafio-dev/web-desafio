package br.com.bycoders.desafiodev.bankingservice;

import br.com.bycoders.desafiodev.bankingservice.domain.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domain.entity.Transactions;
import br.com.bycoders.desafiodev.bankingservice.services.impl.UploadFileServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UploadFileServiceTest {

    @Autowired
    private static UploadFileServiceImpl uploadFileService;

    @Autowired
    private ResourceLoader resourceLoader;

    @BeforeAll
    public static void init() {
        uploadFileService = new UploadFileServiceImpl();
    }

    @Test()
    public void should_save_banking_data_from_store_thoughtout_CNAB_file() throws IOException {
        ClassPathResource arquivoCnab = new ClassPathResource("CNAB.txt");
        MultipartFile input = new MockMultipartFile("CNAB.txt", arquivoCnab.getInputStream());
        Map<Owner, List<Transactions>> transactions = uploadFileService.uploadFile(input);
        Assertions.assertNotNull(transactions);
    }

}
