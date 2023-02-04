package udemy.bruno.expert;

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
			repository.salvar(new Cliente(null, "Douglas"));
			repository.salvar(new Cliente(null, "Leonardo"));

			System.out.println("\nListando clientes");
			var todosClientes = repository.obterTodos();
			todosClientes.forEach(System.out::println);

			System.out.println("\nAtualizando clientes");
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " - ATUALIZADO");
				repository.atualizar(c);
			});

			System.out.println("\nListando clientes com nome %ard%");
			repository.buscarPorNome("ard").forEach(System.out::println);

			System.out.println("\nDeletando clientes");
			todosClientes.forEach(repository::deletar);

			todosClientes = repository.obterTodos();
			if (todosClientes.isEmpty())
				System.out.println("\nNenhum cliente encontrado");
			else
				todosClientes.forEach(System.out::println);
		};
	}

}
