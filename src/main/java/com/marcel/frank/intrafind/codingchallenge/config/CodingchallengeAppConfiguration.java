package com.marcel.frank.intrafind.codingchallenge.config;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.marcel.frank.intrafind.codingchallenge.repository")
public class CodingchallengeAppConfiguration
{
	@Value("${elasticsearch.host}")
	private String EsHost;

	@Value("${elasticsearch.port}")
	private int EsPort;

	@Value("${elasticsearch.clustername}")
	private String EsClusterName;

	@Bean
	public TransportClient client() throws Exception
	{
			Settings esSettings = Settings.builder().put("cluster.name", EsClusterName).build();
			return new PreBuiltTransportClient(esSettings).addTransportAddress(new TransportAddress(InetAddress.getByName(EsHost), EsPort));
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws Exception
	{
		return new ElasticsearchTemplate(client());
	}
}
