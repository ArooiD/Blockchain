package ru.mirea.blockchain.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Slf4j
@Service
public class KafkaService {

    private final KafkaReceiver<String, String> kafkaReceiver;
    private final KafkaSender<String, String> kafkaSender;

    public KafkaService(KafkaReceiver<String, String> kafkaReceiver, KafkaSender<String, String> kafkaSender) {
        this.kafkaReceiver = kafkaReceiver;
        this.kafkaSender = kafkaSender;
    }

    /**
     * Публикация сообщения в указанный топик Kafka.
     *
     * @param topic   название топика
     * @param message сообщение для публикации
     * @return Mono<Boolean> результат отправки
     **/
    public Mono<Boolean> sendMessage(String topic, String message) {
        return kafkaSender.send(Mono.just(SenderRecord.create(topic, null, null, null, message, null)))
                .then(Mono.just(true))
                .onErrorResume(e -> {
                    log.error("Ошибка при отправке сообщения: " + e.getMessage());
                    return Mono.just(false);
                });
    }

    /**
     * Получение сообщений из указанного топика Kafka.
     *
     * @param topic название топика
     * @return Mono<Boolean> результат отправки
     **/
    public Flux<String> consumeMessages(String topic) {
        return kafkaReceiver.receive()
                .map(ReceiverRecord::value)
                .doOnNext(message -> log.info("Получено сообщение: " + message))
                .doOnError(error -> log.error("Ошибка при получении сообщений: " + error.getMessage()));
    }

}
