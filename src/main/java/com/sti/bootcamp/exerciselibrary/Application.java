package com.sti.bootcamp.exerciselibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@Import({DaoSpringBootConfig.class})
@EntityScan({"com.sti.bootcamp.exerciselibrary.model"})//untuk scan apa table di database sudah sesuai sama di entity kalao ga ada ga bakal jalan programnya
@EnableJpaRepositories({"com.sti.bootcamp.exerciselibrary.repository"})
@ComponentScan({"com.sti.bootcamp.exerciselibrary"})
public class Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}

}
