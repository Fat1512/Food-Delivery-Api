package com.food.phat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class KafkaTopicConfig {

    @Autowired
    private Environment env;
//
//    @Bean
//    public NewTopic productDatabaseTopic() {
//        return TopicBuilder
//                .name(Objects.requireNonNull(env.getProperty("product.topic.name")))
//                .build();
//
//    }
}
