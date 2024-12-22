package ru.mirea.blockchain.client.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mirea.blockchain.client.dto.TransactionDTO;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
public class TransactionService {

    private final WebClient webClient;

    public TransactionService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build(); // Настроить базовый URL
    }

    // Метод для генерации случайной транзакции
    private TransactionDTO generateTransaction() {
        return TransactionDTO.builder()
                .transactionId(UUID.randomUUID().toString())
                .senderAddress("sender_" + UUID.randomUUID().toString().substring(0, 6))
                .recipientAddress("recipient_" + UUID.randomUUID().toString().substring(0, 6))
                .amount(BigDecimal.valueOf(Math.random() * 1000))  // случайная сумма
                .fee(BigDecimal.valueOf(Math.random() * 50))  // случайная комиссия
                .timestamp(Instant.now())
                .signature(UUID.randomUUID().toString())  // случайная подпись
                .build();
    }

    // Метод для асинхронной отправки транзакции
    private Mono<String> sendTransaction(TransactionDTO transaction) {
        return webClient.post()
                .uri("/target-endpoint")
                .body(Mono.just(transaction), TransactionDTO.class)
                .retrieve()
                .bodyToMono(String.class);
    }

    // Реактивный поток для периодической отправки транзакций
    public Flux<String> startTransactionSending() {
        return Flux.interval(Duration.ofSeconds(30))  // каждые 30 секунд
                .map(tick -> generateTransaction())  // генерируем транзакцию
                .flatMap(transaction -> sendTransaction(transaction))  // отправляем транзакцию
                .doOnNext(response -> System.out.println("Transaction response: " + response))  // выводим ответ
                .doOnError(error -> System.err.println("Error sending transaction: " + error.getMessage()));  // обработка ошибок
    }
}

