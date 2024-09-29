package com.food.phat.consumer;

import com.food.phat.service.ProductDataSyncService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.food.phat.utils.ProductSyncUtil;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductDataSyncConsumer {

    private final ProductDataSyncService productDataSyncService;

    @KafkaListener(topics = "${product.topic.name}", groupId = "search")
    public void listen(ConsumerRecord<?, ?> consumerRecord) {
        log.info(String.format("Data sync:: %s", consumerRecord.toString()));
        JsonObject json = new Gson().fromJson((String) consumerRecord.value(), JsonObject.class);
        if(json != null) {
            JsonObject payload = json.get("payload").getAsJsonObject();
            if(payload != null) {
                String op = payload.get("op").toString();
                Integer id = Integer.parseInt(payload.get("payload").getAsJsonObject().get("id").toString());
                switch (op) {
                    case ProductSyncUtil.CREATE: productDataSyncService.create(id); break;
                    case ProductSyncUtil.UPDATE: productDataSyncService.update(id); break;
                    case ProductSyncUtil.DELETE: productDataSyncService.delete(id); break;
                }
            }
        }
    }
}
