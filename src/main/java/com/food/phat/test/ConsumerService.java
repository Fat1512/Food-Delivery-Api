package com.food.phat.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

//    @KafkaListener(topics = "${product.topic.name}", groupId = "search")
    public void consumeJsonMsg(ConsumerRecord<?, ?> consumerRecord) {
        log.info(String.format("Consuming the message from alibou Topic:: %s", consumerRecord.toString()));
    }
}
