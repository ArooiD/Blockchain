package ru.mirea.blockchain.client.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class TransactionDTO {
    private String transactionId;
    private String senderAddress;
    private String recipientAddress;
    private BigDecimal amount;
    private BigDecimal fee;
    private Instant timestamp;
    private String signature;
}


