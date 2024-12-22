package ru.mirea.blockchain.client.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.mirea.blockchain.client.service.TransactionService;

@Component
public class TransactionRunner implements CommandLineRunner {

    private final TransactionService transactionService;

    public TransactionRunner(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void run(String... args) {
        // Запускаем поток для отправки транзакций каждые 30 секунд сразу при старте приложения
        transactionService.startTransactionSending()
                .subscribe(); // Начинаем процесс отправки транзакций асинхронно
    }
}

