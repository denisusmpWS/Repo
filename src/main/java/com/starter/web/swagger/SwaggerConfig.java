package com.starter.web.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Comparativa:
 * Con un archivo .properties -> para la lectura desde una clase a traves del @Value se requiere la anotacion @ConfigurationProperties + @Configuration
 * Con un archivo .yaml -> para la lectura desde una clase a traves del @Value se requiere la anotacion @Configuration nada mas.
 * @author hp
 *
 */
@EnableSwagger2
@Configuration
//@ConfigurationProperties
public class SwaggerConfig {

	@Value("${server.controller.context-path}.*") //para tomar todos los context base
	private String path;

	@Value("${application.swagger.title}")
	private String title;
	
	@Value("${application.swagger.description}")
	private String description;
	
	@Value("${application.swagger.version}")
	private String version;
	
	@Value("${application.swagger.groupName}")
	private String groupName;
	
	@Bean
	public Docket docket(){
		return new Docket(DocumentationType.SWAGGER_2)
							.groupName(groupName)
							.apiInfo(apiInfo())
							.select()
							.paths(productEndpoint())
							.build();
	}
	
	
	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title(title)
				.description(description)
				.termsOfServiceUrl("http://heidloff.net/article/usage-of-swagger-2-0-in-spring-boot-applications-to-document-apis/")
				.license("Apache License Version 2.0")
				.licenseUrl("http://gitlab.com/")
				.version(version)
				.build();
	}

	private Predicate<String> productEndpoint(){
		return regex(path);
	}
}
