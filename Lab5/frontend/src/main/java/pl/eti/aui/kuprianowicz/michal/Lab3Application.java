package pl.eti.aui.kuprianowicz.michal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab3Application {

	public static void main(String[] args) {
		System.out.println("before spring");
		SpringApplication.run(Lab3Application.class, args);
		System.out.println("after spring");
	}

}
