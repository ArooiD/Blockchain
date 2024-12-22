package ru.mirea.blockchain.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.mirea.blockchain.server.dto.TransactionDTO;

@Slf4j
@Service
public class TransactionService {
    private final KafkaProducerService kafkaProducerService;
    private final ObjectMapper mapper;

    public TransactionService(KafkaProducerService kafkaProducerService, ObjectMapper mapper) {
        this.kafkaProducerService = kafkaProducerService;
        this.mapper = mapper;
    }

    public Mono<Boolean> sendMessage(String topic, TransactionDTO transaction) {
        try {
            String message = mapper.writeValueAsString(transaction);
            log.info(message);
            return kafkaProducerService.sendMessage(topic, message);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Mono.just(Boolean.FALSE);
        }
    }
}
