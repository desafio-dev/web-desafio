package br.com.bycoders.desafiodev.bankingservice.domains.enums;

public enum TypeOperationEnum {

    DEBITO(1, "Débito", 1),
    BOLETO(2, "Boleto", 0),
    FINANCIAMENTO(3, "Financiamento", 0),
    CREDITO(4, "Crédito", 1),
    REEBIMENTO_EMPRESTIMO(5, "Recebimento empréstimo", 1),
    VENDAS(6, "Vendas", 1),
    REEBIMENTO_TED(7, "Recebimento TED", 1),
    REEBIMENTO_DOC(8, "Recebimento DOC", 1),
    ALUGUEL(9, "Aluguel", 0);

    private final int type;
    private final String description;
    private final int operation;

    TypeOperationEnum(int type, String description, int operation) {
        this.type = type;
        this.description = description;
        this.operation = operation;
    }

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getOperation() {
        return operation;
    }

    public static TypeOperationEnum getOperationByTypeOperation(int typeOperation) {
        for (TypeOperationEnum transaction : values()) {
            if (transaction.getType() == typeOperation) {
                return transaction;
            }
        }
        throw new IllegalArgumentException("Tipo de transação inválido");
    }
}
