package com.muhardin.endy.aplikasiregistrasi;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@SpringBootApplication
public class AplikasiRegistrasiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplikasiRegistrasiApplication.class, args);
	}

	public static final String CLIENT_SECRET_JSON_ENV = "CLIENT_SECRET_JSON";
	public static final String STORED_CREDENTIAL_ENV = "STORED_CREDENTIAL";
	public static final String CLIENT_SECRET_JSON_FILE = "client_secret.json";
	public static final String STORED_CREDENTIAL_FILE = "StoredCredential";

	@Value("${gmail.folder}")
	private String dataStoreFolder;

	@Autowired
	private Environment env;

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}

	@Bean @Profile("!heroku")
	public GoogleClientSecrets localFileClientSecrets() throws Exception {
		return loadGoogleClientSecrets();
	}

	@Bean @Profile("heroku")
	public GoogleClientSecrets environmentVariableClientSecrets() throws Exception {
		restoreClientSecret();
		restoreStoredCredential();
		return loadGoogleClientSecrets();
	}

	private GoogleClientSecrets loadGoogleClientSecrets() throws IOException {
		return GoogleClientSecrets.load(jsonFactory(),
				new InputStreamReader(new FileInputStream(dataStoreFolder + File.separator + CLIENT_SECRET_JSON_FILE)));
	}

	private void restoreStoredCredential() throws IOException {
		Files.createDirectories(Paths.get(dataStoreFolder));
		Files.write(Paths.get(dataStoreFolder +
						File.separator + STORED_CREDENTIAL_FILE),
				Base64.getDecoder().decode(env.getProperty(STORED_CREDENTIAL_ENV)));
	}

	private void restoreClientSecret() throws IOException {
		Files.createDirectories(Paths.get(dataStoreFolder));
		Files.write(Paths.get(dataStoreFolder +
						File.separator + CLIENT_SECRET_JSON_FILE),
				Base64.getDecoder().decode(env.getProperty(CLIENT_SECRET_JSON_ENV)));
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
