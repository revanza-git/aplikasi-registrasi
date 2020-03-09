package com.revanza.raytama;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
public class AplikasiRegistrasiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplikasiRegistrasiApplication.class, args);
	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}

	@Bean
	public JsonFactory jsonFactory(){
		return JacksonFactory.getDefaultInstance();
	}

	@Bean
	public MustacheFactory mustacheFactory(){
		return new DefaultMustacheFactory();
	}

}
