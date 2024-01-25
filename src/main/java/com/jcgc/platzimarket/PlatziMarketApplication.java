package com.jcgc.platzimarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {
	"com.jcgc.platzimarket.persistence.mapper",
		"com.jcgc.platzimarket.persistence.crud",
		"com.jcgc.platzimarket.persistence.entity",
		"com.jcgc.platzimarket.persistence.repository",
		"com.jcgc.platzimarket.domain"
})
@SpringBootApplication
public class PlatziMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatziMarketApplication.class, args);
	}

}
