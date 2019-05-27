package com.kolibreath.j2eefinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class J2eefinalApplication {

	@RequestMapping("/")
	public String index(){
		return "fuck you ";
	}
	public static void main(String[] args) {
		SpringApplication.run(J2eefinalApplication.class, args);
	}

}
