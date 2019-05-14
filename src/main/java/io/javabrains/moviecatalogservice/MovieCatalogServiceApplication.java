 package io.javabrains.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
public class MovieCatalogServiceApplication{
	
	
	@Bean
	@LoadBalanced
	/*  The @LoadBalanced does the service discovery in the load balanced way.
	  It basically tells to the RestTemplate that, dont go to the service directly,
	   whatever the URL given to you is not the actual URL(Its only a hint about what service to discover)
*/	
	public RestTemplate getRestTemplate() {
	return new RestTemplate();
	}
	
	
	@Bean
	public void getWebClientBuilder(){
		WebClient.Builder builder=WebClient.builder();
	
	}
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}
}