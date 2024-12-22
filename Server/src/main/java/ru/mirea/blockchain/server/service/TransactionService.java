package ru.mirea.blockchain.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mirea.blockchain.server.dto.TransactionDTO;

import java.util.Objects;

@Slf4j
@Service
public class TransactionService {
    private final KafkaService kafkaService;
    private final ObjectMapper mapper;

    public TransactionService(KafkaService kafkaProducerService, ObjectMapper mapper) {
        this.kafkaService = kafkaProducerService;
        this.mapper = mapper;
    }

    public Mono<Boolean> sendMessage(TransactionDTO transaction) {
        try {
            String message = mapper.writeValueAsString(transaction);
            log.info(message);
            return kafkaService.sendMessage("transaction", message);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Mono.just(Boolean.FALSE);
        }
    }

    public Flux<TransactionDTO> getAllTransactions() {
        return kafkaService.consumeMessages("transaction")
                .mapNotNull(message -> {
                    try {
                        return mapper.convertValue(message, TransactionDTO.class);
                    } catch (Exception e) {
                        System.err.println("Ошибка преобразования сообщения: " + e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .doOnNext(dto -> System.out.println("Получена транзакция: " + dto))
                .doOnError(e -> System.err.println("Ошибка при обработке сообщений: " + e.getMessage()));
    }

}
