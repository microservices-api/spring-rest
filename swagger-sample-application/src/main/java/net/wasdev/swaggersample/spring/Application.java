package net.wasdev.swaggersample.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//------------------------------------------------------------------------------------------------
// It equals to @Configuration + @EnableAutoConfiguration + @ComponentScan
//------------------------------------------------------------------------------------------------
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
