package nl.cybernetix.demo;

import lombok.RequiredArgsConstructor;
import nl.cybernetix.demo.actors.Waitress;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
