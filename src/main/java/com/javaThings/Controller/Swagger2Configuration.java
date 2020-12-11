package com.javaThings.Controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {
	public static final Contact CONTACT = new Contact("Vijay Jatti", "www.tutorialpoint.com", "vijay.jatti01@gmail.com");
	  public static final ApiInfo DEFAULT_API_DETAILS = new ApiInfo("Awesome Site", "Api Details", "1.0", "urn:tos",
	          CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	private static final HashSet<String> produces_and_consumes =new HashSet<String>(Arrays.asList("application/json","application/xml"));

	@Bean
	public Docket setDocket()
	{
		
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_DETAILS)
			.produces(produces_and_consumes).consumes(produces_and_consumes).select()
		         .apis(RequestHandlerSelectors.basePackage("com.javaThings")).build();
	}
}
