package com.marcel.frank.intrafind.codingchallenge;

import com.marcel.frank.intrafind.codingchallenge.model.User;
import com.marcel.frank.intrafind.codingchallenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@SpringBootApplication
public class CodingchallengeApplication
{
	@Autowired
	private UserService userService;

	@Autowired
	private ElasticsearchOperations elasticsearchTemplate;

	public static void main(String[] args)
	{
		SpringApplication.run(CodingchallengeApplication.class, args);
	}
}
