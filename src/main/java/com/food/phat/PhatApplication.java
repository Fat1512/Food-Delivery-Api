package com.food.phat;

import com.food.phat.entity.Token;
import com.food.phat.repository.TokenRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.index.IndexConfiguration;
import org.springframework.data.redis.core.index.IndexDefinition;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.food.phat.repository")
@EnableRedisRepositories(basePackages = "com.food.phat.repository")
public class PhatApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhatApplication.class, args);
	}


//	public static class RedisIndexConfiguration extends IndexConfiguration {
//		@Override
//		protected Iterable<? extends IndexDefinition> initialConfiguration() {
//			return Collections;
//		}
//	}

}

@Component
@Slf4j
class RedisExample implements CommandLineRunner {
	@Autowired
	private TokenRedisRepository tokenRedisRepository;
	@Autowired
	private RedisTemplate<Object, Token> redisTemplate;

	@Override
	public void run(String... args) throws Exception {

//		Token token1 = Token.builder()
//				.userKey(1000000)
//				.uuid(UUID.randomUUID().toString())
//				.timeToLive(200000)
//				.build();
//		Token token2 = Token.builder()
//				.userKey(1000000)
//				.uuid(UUID.randomUUID().toString())
//				.timeToLive(200000)
//				.build();
//		tokenRedisRepository.save(token1);
//		tokenRedisRepository.save(token2);
//		List<Token> tokens = tokenRedisRepository.findByUserKey(1000000);
//		tokenRedisRepository.deleteAllById(tokens.stream().map(Token::getUuid).toList());
//		log.info("tokens: {}", tokens);
	}
}