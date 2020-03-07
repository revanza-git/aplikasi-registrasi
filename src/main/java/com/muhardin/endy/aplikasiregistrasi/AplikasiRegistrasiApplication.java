package com.muhardin.endy.aplikasiregistrasi;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AplikasiRegistrasiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplikasiRegistrasiApplication.class, args);
	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
}
