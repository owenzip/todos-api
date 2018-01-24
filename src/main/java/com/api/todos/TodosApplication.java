/**
 * TaskApplication.java
 * Created by NhutNguyen
 * Date 20/11/2017	13:00 PM
 */
package com.api.todos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.api")
@EntityScan("com.api")
@EnableJpaRepositories("com.api")
public class TodosApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TodosApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TodosApplication.class);
    }

	/* CONFIGURE SWAGGER UI */
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("TodoApp").apiInfo(apiInfo()).select()
				.paths(regex("/*.*")).build();
	}

	/* CONFIGURE INFORMATION API */
	private ApiInfo apiInfo() {
		
		ApiInfo apiInfo = new ApiInfo("REST API Todo Application", "API Document", "1.0", "Terms of com.api.service",
				new Contact("Nhut Nguyen", "", ""), "", "");
		return apiInfo;
	}
}
