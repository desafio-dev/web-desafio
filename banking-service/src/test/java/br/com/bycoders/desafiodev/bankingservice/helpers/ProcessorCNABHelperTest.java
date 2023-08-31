package br.com.bycoders.desafiodev.bankingservice.helpers;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public class ProcessorCNABHelperTest {

    @Autowired
    private static ProcessorCNABHelper processorCnab;

    @BeforeAll
    public static void init() {
        processorCnab = new ProcessorCNABHelper();
    }


    @Test
    public void should_read_a_cnab_file() throws IOException {
        ClassPathResource arquivoCnab = new ClassPathResource("CNAB.txt");
        MultipartFile input = new MockMultipartFile("CNAB.txt", arquivoCnab.getInputStream());
        Map<Owner, List<Transactions>> transactions = processorCnab.interpreteFile(input);

        transactions.entrySet().stream().forEach(entry -> {
            Owner owner = entry.getKey();
            List<Transactions> transactionsList = entry.getValue();

            Assertions.assertEquals(11, owner.getCpf().length());
            Assertions.assertEquals(14, owner.getOwnerName().length());

            transactionsList.forEach(transaction -> {
                Assertions.assertEquals(12, transaction.getCardNumber().length());
                Assertions.assertEquals(10, transaction.getValue().length());
                Assertions.assertEquals(1, transaction.getTypeOperation().length());
                Assertions.assertTrue( transaction.getDate() instanceof ZonedDateTime );
            });
        });
    }
}
