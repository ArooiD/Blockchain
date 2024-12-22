package ru.mirea.blockchain.client.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mirea.blockchain.client.dto.TransactionDTO;

@Service
@RequiredArgsConstructor
public class TransactionService {

    @Value("${transaction.endpoint}")
    private String endpoint;

    private final RestTemplate restTemplate;

    // Отправка транзакции каждые 30 секунд
    @Scheduled(fixedRate = 30000) // Отправлять каждые 30 секунд
    public void sendTransaction() {
        try {
            // Генерация случайной транзакции
            TransactionDTO transaction = TransactionDTO.generateRandomTransaction();
            restTemplate.postForObject(endpoint, transaction, String.class);
            System.out.println("Transaction sent: " + transaction);
        } catch (Exception e) {
            System.err.println("Error sending transaction: " + e.getMessage());
        }
    }
}
