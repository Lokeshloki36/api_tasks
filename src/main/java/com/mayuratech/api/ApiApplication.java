package com.mayuratech.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mayuratech.api.logging.Log4JDemo;

//import com.mayuratech.api.logging.Log4JDemo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Api Documentation",description = "Api Task Implementations"))
public class ApiApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	
	}
	@Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedHeaders("*").allowedOrigins("http://localhost:4200","http://localhost:57704");
            }
        };
    }	

	
	@Override
	public void run(String... args) throws Exception {
		Log4JDemo log = new Log4JDemo();
		log.logs();
		
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	
}
