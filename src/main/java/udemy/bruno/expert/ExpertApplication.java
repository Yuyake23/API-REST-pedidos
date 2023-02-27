package udemy.bruno.expert;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import udemy.bruno.expert.domain.entities.Cliente;
import udemy.bruno.expert.domain.repositories.ClienteRepository;
import udemy.bruno.expert.domain.repositories.PedidoRepository;

@SpringBootApplication
public class ExpertApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpertApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository clienteRepository,
			@Autowired PedidoRepository pedidoRepository) {
		return args -> {
			Cliente c1 = new Cliente(null, "11111111111", "Douglas Vieira");
			Cliente c2 = new Cliente(null, "22222222222", "Leonardo Silva");
			Cliente c3 = new Cliente(null, "33333333333", "Joaquim Rezende");
			clienteRepository.saveAll(Arrays.asList(c1, c2, c3));
		};
	}

}
