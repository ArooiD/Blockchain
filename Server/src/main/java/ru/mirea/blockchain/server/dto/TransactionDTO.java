package ru.mirea.blockchain.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private String transactionId;
    private String senderAddress;
    private String recipientAddress;
    private BigDecimal amount;
    private BigDecimal fee;
    private Instant timestamp;
    private String signature;

    public static TransactionDTO generateRandomTransaction() {
        TransactionDTO transaction = new TransactionDTO();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setSenderAddress("sender_" + UUID.randomUUID());
        transaction.setRecipientAddress("recipient_" + UUID.randomUUID());
        transaction.setAmount(BigDecimal.valueOf(Math.random() * 1000).setScale(2, RoundingMode.HALF_UP)); // случайная сумма
        transaction.setFee(BigDecimal.valueOf(Math.random() * 10).setScale(2, RoundingMode.HALF_UP)); // случайная комиссия
        transaction.setTimestamp(Instant.now()); // текущее время
        transaction.setSignature(UUID.randomUUID().toString()); // случайная подпись
        return transaction;
    }
}

