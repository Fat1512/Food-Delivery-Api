package com.food.phat;

import com.food.phat.entity.Token;
import com.food.phat.repository.TokenRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.food.phat.repository")
public class PhatApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhatApplication.class, args);
	}

}

@Component
class RedisExample implements CommandLineRunner {
	@Autowired
	private TokenRedisRepository tokenRedisRepository;

	@Override
	public void run(String... args) throws Exception {

		Token token = new Token();
		token.setUuid("asd");
		token.setUserKey(12321);
		tokenRedisRepository.save(token);
		// In ra màn hình Giá trị của key "loda" trong Redis
	}
}