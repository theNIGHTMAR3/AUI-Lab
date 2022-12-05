package pl.eti.aui.kuprianowicz.michal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab3LibraryApplication {

	public static void main(String[] args) {
		System.out.println("before spring");
		SpringApplication.run(Lab3LibraryApplication.class, args);
		System.out.println("after spring");
	}

}
