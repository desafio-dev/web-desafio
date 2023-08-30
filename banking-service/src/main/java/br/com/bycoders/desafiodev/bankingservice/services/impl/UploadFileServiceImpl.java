package br.com.bycoders.desafiodev.bankingservice.services.impl;

import br.com.bycoders.desafiodev.bankingservice.domain.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domain.entity.Transactions;
import br.com.bycoders.desafiodev.bankingservice.domain.records.TransactionRecord;
import br.com.bycoders.desafiodev.bankingservice.services.UploadFileService;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UploadFileServiceImpl implements UploadFileService {

    @Override
    public Map<Owner, List<Transactions>> uploadFile(MultipartFile cnabFile) throws IOException {

        List<TransactionRecord> transactionRecords = new ArrayList<>();
        Map<Owner, List<Transactions>> operations = new HashMap<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(cnabFile.getInputStream()))) {
            String row;

            while ((row = reader.readLine()) != null) {
                String operations_type = row.substring(0, 1);
                String date = row.substring(1, 9);
                String value = row.substring(9, 19);
                String cpf = row.substring(19, 30);
                String card_number = row.substring(30, 42);
                String hour = row.substring(42, 48);
                String owner = row.substring(48, 62);
                String name_store = row.substring(62, 80);

                Owner ownerToSave = Owner.builder().ownerName(owner).cpf(cpf).build();

                if (!operations.containsKey(ownerToSave)) {
                    List<Transactions> transaction = new ArrayList<>();
                    transaction.add(Transactions.builder()
                            .typeOperation(operations_type)
                                    .value(value)
                                    .cardNumber(card_number)
                            .build()
                    );
                    operations.put(ownerToSave, transaction);
                } else {
                    operations.get(ownerToSave).add(Transactions.builder()
                            .typeOperation(operations_type)
                            .value(value)
                            .cardNumber(card_number).build());

                }
            }
        }



        return operations;

    }
}
