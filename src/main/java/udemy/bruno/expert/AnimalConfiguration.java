package udemy.bruno.expert;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalConfiguration {
	
	@Bean
	public Animal cachorro() {
		return () -> System.out.println("Au Au");
	}
	
	@Bean
	public Animal gato() {
		return () -> System.out.println("Miau");
	}
}
