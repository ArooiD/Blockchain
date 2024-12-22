package ru.mirea.blockchain.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TransactionDTO {

    private String transactionId; // Уникальный идентификатор транзакции
    private String senderAddress; // Адрес отправителя
    private String recipientAddress; // Адрес получателя
    private BigDecimal amount; // Сумма перевода
    private BigDecimal fee; // Комиссия за транзакцию
    private Instant timestamp; // Время создания транзакции
    private String signature; // Цифровая подпись отправителя

    // Метод для генерации случайных значений
    public static TransactionDTO generateRandomTransaction() {
        TransactionDTO transaction = new TransactionDTO();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setSenderAddress("sender_" + UUID.randomUUID().toString());
        transaction.setRecipientAddress("recipient_" + UUID.randomUUID().toString());
        transaction.setAmount(BigDecimal.valueOf(Math.random() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP)); // случайная сумма
        transaction.setFee(BigDecimal.valueOf(Math.random() * 10).setScale(2, BigDecimal.ROUND_HALF_UP)); // случайная комиссия
        transaction.setTimestamp(Instant.now()); // текущее время
        transaction.setSignature(UUID.randomUUID().toString()); // случайная подпись
        return transaction;
    }
}

