package br.ifpe.pg.provacolegiada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProvaColegiadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvaColegiadaApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("1234"));
	}

}

