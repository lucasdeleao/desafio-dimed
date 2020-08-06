package br.com.deleaolucas.desafiodimed.linhaonibus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class BusLineMsApplication {
	public static void main(String[] args) {
		SpringApplication.run(BusLineMsApplication.class, args);
	}
}
