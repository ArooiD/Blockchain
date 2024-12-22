package ru.mirea.blockchain.server.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Публикация сообщения в указанный топик Kafka.
     *
     * @param topic   название топика
     * @param message сообщение для публикации
     * @return Mono<Boolean> результат отправки
     */
    public Mono<Boolean> sendMessage(String topic, String message) {
        return Mono.fromFuture(kafkaTemplate.send(topic, message.toString())).map(sendResult -> {
            System.out.println("Сообщение успешно отправлено в топик: " + topic + " с offset: " + sendResult.getRecordMetadata().offset());
            return true;
        }).onErrorResume(e -> {
            System.err.println("Ошибка при отправке сообщения: " + e.getMessage());
            return Mono.just(false);
        });
    }
}
