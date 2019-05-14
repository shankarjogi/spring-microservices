package io.javabrains.moviecatalogservice.resources;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient.RequestHeadersUriSpec;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		/*m
		RestTemplate restTemplate=new RestTemplate();*/
		/*WebClient.Builder builder=WebClient.builder();*/
		UserRating ratings=restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+ userId,UserRating.class);

		
		
		return ratings.getUserRating().stream().map(rating-> {
			
			
		Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
		

			
		return new CatalogItem(movie.getName(), "Test", rating.getRating());
		})
				.collect(Collectors.toList());
		
		//get all rated movie IDs
		
		//For each movie Id, call movie info service and get details
		
				
		//Put them all together
		
	/*	return Collections.singletonList(new CatalogItem("Transformers", "Test",4));*/
				

}
}



//Using WebClient making API calls//
/*	Movie movie=webClientBuilder.build()
	.get()
	.uri("http://localhost:8082/movies/"+ rating.getMovieId())
	.retrieve()
	.bodyToMono(Movie.class)
	.block();*/