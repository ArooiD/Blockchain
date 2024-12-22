package ru.mirea.blockchain.client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.blockchain.client.dto.TransactionDTO;
import ru.mirea.blockchain.client.service.TransactionService;

@RestController
public class TransactionController {

    private final ObjectMapper objectMapper;
    private final TransactionService transactionService;

    public TransactionController(ObjectMapper objectMapper, TransactionService transactionService) {
        this.transactionService = transactionService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/start-sending-transactions")
    public String startSendingTransactions() {
        // Отправка транзакций начинается автоматически, так что этот метод можно не использовать
        return "Transaction sending started!";
    }

    // Этот эндпоинт будет принимать данные транзакции и выводить их в консоль
    @PostMapping("/target-endpoint")
    public String handleTransaction(@RequestBody TransactionDTO transaction) {
        try {
            // Преобразуем объект в JSON строку и выводим в консоль
            String transactionJson = objectMapper.writeValueAsString(transaction);
            System.out.println("Received transaction: " + transactionJson);
        } catch (Exception e) {
            System.err.println("Error while converting transaction to JSON: " + e.getMessage());
        }
        return "Transaction received successfully!";
    }
}


