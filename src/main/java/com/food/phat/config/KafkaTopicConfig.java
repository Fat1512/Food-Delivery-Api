package com.food.phat.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Objects;

@Configuration
@PropertySource("classpath:application.properties")
public class KafkaTopicConfig {

    @Autowired
    private Environment env;

    @Bean
    public NewTopic productDatabaseTopic() {
        return TopicBuilder
                .name(Objects.requireNonNull(env.getProperty("product.topic.name")))
                .build();

    }
}
