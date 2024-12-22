package ru.mirea.blockchain.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.blockchain.client.dto.TransactionDTO;
import ru.mirea.blockchain.client.service.TransactionService;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/start-sending-transactions")
    public String startSendingTransactions() {
        // Отправка транзакций начинается автоматически, так что этот метод можно не использовать
        return "Transaction sending started!";
    }

    // Этот эндпоинт будет принимать данные транзакции и выводить их в консоль
    @PostMapping("/target-endpoint")
    public String handleTransaction(@RequestBody TransactionDTO transaction) {
        System.out.println("Received transaction: " + transaction);
        return "Transaction received successfully!";
    }
}


