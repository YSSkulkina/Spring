package ru.skulkina.project3RestApp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class Project3RestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project3RestAppApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();}


}
