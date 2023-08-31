package br.com.bycoders.desafiodev.bankingservice.helpers;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessorCNABHelper {

    public static Map<Owner, List<Transactions>> interpreteFile(MultipartFile file) throws IOException {

        return readFileAndExtractToHashMapOwnerAndListTransactions(file);
    }

    private static Map<Owner, List<Transactions>> readFileAndExtractToHashMapOwnerAndListTransactions(MultipartFile file) throws IOException {
        Map<Owner, List<Transactions>> operations = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String row;
            while ((row = reader.readLine()) != null) {
                Owner ownerToSave = createOwner(row);
                validateOwnerAndHandleTransactionIntoHashMap(operations, ownerToSave, row);
            }
        }
        return operations;
    }

    private static Owner createOwner(String row) {
        String cpf = row.substring(19, 30);
        String ownerName = row.substring(48, 62);
        String storeName = row.substring(62, 80);
        return Owner.builder().ownerName(ownerName.trim()).cpf(cpf).storeName(storeName.trim()).build();
    }

    private static void validateOwnerAndHandleTransactionIntoHashMap(Map<Owner, List<Transactions>> operations, Owner ownerToSave, String row) {
        if (!operations.containsKey(ownerToSave)) {
            insertOwnerInTheMapIfNotExists(operations, ownerToSave, row);
        } else {
            operations.get(ownerToSave).add(createTransaction(row));

        }
    }

    private static void insertOwnerInTheMapIfNotExists(Map<Owner, List<Transactions>> operations, Owner ownerToSave, String row) {

        List<Transactions> transaction = new ArrayList<>();
        transaction.add(createTransaction(row));
        operations.put(ownerToSave, transaction);
    }

    private static Transactions createTransaction(String row) {
        String operations_type = row.substring(0, 1);
        String data = row.substring(1, 9);
        String value = row.substring(9, 19);
        String card_number = row.substring(30, 42);
        String hour = row.substring(42, 48);

        String year = data.substring(0, 4);
        String month = data.substring(4, 6);
        String day = data.substring(6, 8);

        String hours = hour.substring(0, 2);
        String minutes = hour.substring(2, 4);
        String seconds = hour.substring(4, 6);

        ZonedDateTime dateTime = ZonedDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(hours), Integer.parseInt(minutes), Integer.parseInt(seconds), 0, ZoneId.systemDefault());

        return Transactions.builder()
                .typeOperation(operations_type)
                .value(value)
                .cardNumber(card_number)
                .date(dateTime)
                .build();
    }


}
