package ru.mirea.blockchain.server.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
public class TransactionDTO {
    private String transactionId;
    private String senderAddress;
    private String recipientAddress;
    private Double amount;
    private Double fee;
    private Date timestamp;
    private String signature;

    @JsonCreator
    public TransactionDTO(
            @JsonProperty("transactionId") String transactionId,
            @JsonProperty("senderAddress") String senderAddress,
            @JsonProperty("recipientAddress") String recipientAddress,
            @JsonProperty("amount") Double amount,
            @JsonProperty("fee") Double fee,
            @JsonProperty("timestamp") Date timestamp,
            @JsonProperty("signature") String signature) {
        this.transactionId = transactionId;
        this.senderAddress = senderAddress;
        this.recipientAddress = recipientAddress;
        this.amount = amount;
        this.fee = fee;
        this.timestamp = timestamp;
        this.signature = signature;
    }
}

