package edu.eci.arsw.wordleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.wordle"})
public class VsWordleAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(VsWordleAPIApplication.class, args);
	}
}
