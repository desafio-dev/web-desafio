package br.com.bycoders.desafiodev.bankingservice.helpers;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        String owner = row.substring(48, 62);
        return Owner.builder().ownerName(owner).cpf(cpf).build();
    }

    private static void validateOwnerAndHandleTransactionIntoHashMap(Map<Owner, List<Transactions>> operations, Owner ownerToSave, String row) {
        String operations_type = row.substring(0, 1);
        String value = row.substring(9, 19);
        String card_number = row.substring(30, 42);

        if (!operations.containsKey(ownerToSave)) {
            insertOwnerInTheMapIfNotExists(operations, ownerToSave, operations_type, value, card_number);
        } else {
            operations.get(ownerToSave).add(createTransaction(operations_type, value, card_number));

        }
    }

    private static void insertOwnerInTheMapIfNotExists(Map<Owner, List<Transactions>> operations, Owner ownerToSave, String operations_type, String value, String card_number) {
        List<Transactions> transaction = new ArrayList<>();
        transaction.add(createTransaction(operations_type, value, card_number));
        operations.put(ownerToSave, transaction);
    }

    private static Transactions createTransaction(String operations_type, String value, String card_number) {
        return Transactions.builder()
                .typeOperation(operations_type)
                .value(value)
                .cardNumber(card_number)
                .build();
    }
}
