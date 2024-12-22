package ru.mirea.blockchain.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.mirea.blockchain.server.dto.TransactionDTO;
import ru.mirea.blockchain.server.service.TransactionService;

@RestController
@RequestMapping("api")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Отправка сообщения в Kafka топик.
     *
     * @param transaction тело сообщения
     * @return Mono<ResponseEntity<Void>>
     */
    @PostMapping("transactions")
    public Mono<Boolean> sendTransaction(
            @RequestBody TransactionDTO transaction) {
        return transactionService.sendMessage("transaction", transaction);
    }
}
