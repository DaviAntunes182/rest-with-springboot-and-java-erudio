package br.com.erudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	//Bean -> Objeto instânciado e gerenciado pelo spring IOC container
	@Bean 
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
					.title("RESTful API with JAVA 17 and Springboot 3")
					.version("v1")
					.description("Descrição")
					.termsOfService("https://pub.erudio.com.br/meus-cursos")
					.license(
							new License()
								.name("Apache 2.0")
								.url("https://pub.erudio.com.br/meus-cursos")
							)
					); //http://localhost:8090/v3/api-docs
	}
}