package ru.mirea.blockchain.client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.mirea.blockchain.client.dto.TransactionDTO;
import ru.mirea.blockchain.client.service.TransactionService;

@RestController
public class TransactionController {

    private final TransactionService transactionService;
    private final ObjectMapper objectMapper;

    public TransactionController(ObjectMapper objectMapper, TransactionService transactionService) {
        this.transactionService = transactionService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/start-sending-transactions")
    public Mono<String> startSendingTransactions() {
        transactionService.startTransactionSending()
                .subscribe();  // Начинаем отправку транзакций асинхронно
        return Mono.just("Transaction sending started!");
    }

    @PostMapping("/target-endpoint")
    public String handleTransaction(@RequestBody TransactionDTO transaction) {
        try {
            String transactionJson = objectMapper.writeValueAsString(transaction);
            System.out.println("Received transaction: " + transactionJson);
        } catch (Exception e) {
            System.err.println("Error while converting transaction to JSON: " + e.getMessage());
        }
        return "Transaction received successfully!";
    }
}



