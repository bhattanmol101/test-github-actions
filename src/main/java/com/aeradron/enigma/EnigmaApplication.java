package com.aeradron.enigma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
public class EnigmaApplication {

	private static final Logger log = LoggerFactory
			.getLogger(EnigmaApplication.class);

	public static void main(String[] args) {
		log.info("----------------------- APPLICATION START INITIATED -----------------------");
		SpringApplication.run(EnigmaApplication.class, args);
		log.info("----------------------- APPLICATION STARTED -----------------------");
	}

	@Bean
	public ServletRegistrationBean dispatcherRegistration() {
		return new ServletRegistrationBean(dispatcherServlet());
	}

	@Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
	public DispatcherServlet dispatcherServlet() {
		return new LoggerDispatcherServlet();
	}

	@Bean
	public Docket enigmaApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).useDefaultResponseMessages(false)
				.directModelSubstitute(Object.class, java.lang.Void.class).select().paths(regex("/v1.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Enigma API").description(
				"This defines all the API for the Enigma backend.")
				.license("aeradron Licensed").version("1.0")
				.build();
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		return new MultipartConfigElement("");
	}

	@Bean
	public MultipartResolver multipartResolver() {
		org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000);
		return multipartResolver;
	}

}