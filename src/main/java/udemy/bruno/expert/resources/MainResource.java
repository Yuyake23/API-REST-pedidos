package udemy.bruno.expert.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import udemy.bruno.expert.Animal;
import udemy.bruno.expert.Cachorro;

@RestController
public class MainResource {

	@Autowired
	@Qualifier("mainView")
	private String mainView;

	@Value("${application.name}")
	private String applicationName;
	@Cachorro
	private Animal animal;

	@GetMapping("/main")
	public ResponseEntity<String> helloWorld() {
		return ResponseEntity.ok(mainView);
	}

	@GetMapping("/name")
	public ResponseEntity<String> name() {
		return ResponseEntity.ok(applicationName);
	}

	@Bean("exucutarAnimal")
	public CommandLineRunner executar() {
		return args -> this.animal.fazerBarulho();
	}

}
