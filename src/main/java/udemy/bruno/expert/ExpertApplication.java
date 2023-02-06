package udemy.bruno.expert;

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
			Cliente c1 = new Cliente(null, "Douglas");
			clienteRepository.save(c1);
		};
	}

}
