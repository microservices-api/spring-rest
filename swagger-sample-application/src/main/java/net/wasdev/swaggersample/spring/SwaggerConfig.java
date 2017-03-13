package net.wasdev.swaggersample.spring;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//------------------------------------------------------------------------------------------------
// Docket builds a JSON file. @Configuration is set for display JSON (on Swagger UI)
//------------------------------------------------------------------------------------------------
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		singletons.add(new ResourceController());
		return singletons;
	}


	@Bean
	public Docket documentation() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false) //<-- this should be false
				.select()
					.apis(RequestHandlerSelectors.any())
					.paths(regex("^/(?!error).*$"))
					.build()
				.apiInfo(metadata());
	}

	private ApiInfo metadata(){
		return new ApiInfoBuilder()
				.title("Liberty REST APIs")
				.version("")
				.build();
	}
}
