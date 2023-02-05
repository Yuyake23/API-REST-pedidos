package udemy.bruno.expert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import udemy.bruno.expert.domain.entities.Cliente;
import udemy.bruno.expert.domain.repositories.ClienteRepository;

@SpringBootApplication
public class ExpertApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpertApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository repository) {
		return args -> {
			System.out.println("Salvando clientes");
			repository.save(new Cliente(null, "Douglas"));
			repository.save(new Cliente(null, "Leonardo"));

			System.out.println("\nListando clientes");
			List<Cliente> todosClientes = repository.findAll();
			todosClientes.forEach(System.out::println);

			boolean existe = repository.existsByNome("Douglas");
			System.out.println("Existe cliente com nome Douglas? " + (existe ? "sim" : "não"));

		};
	}

}
