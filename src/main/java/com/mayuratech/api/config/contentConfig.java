package com.mayuratech.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class contentConfig implements WebMvcConfigurer{
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		WebMvcConfigurer.super.configureContentNegotiation(configurer);
		configurer.favorParameter(true)
		.parameterName("mediaType")
		.defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("html", MediaType.TEXT_HTML)
		.mediaType("xml", MediaType.APPLICATION_XML)
		.mediaType("text", MediaType.TEXT_PLAIN);
	}
}
