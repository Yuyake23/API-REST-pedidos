package udemy.bruno.expert.rest.controller;

import static udemy.bruno.expert.rest.controller.ResponseStatusExceptionSupplier.notFound;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import udemy.bruno.expert.domain.entities.Cliente;
import udemy.bruno.expert.domain.repositories.ClienteRepository;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{id}")
	public Cliente findById(@PathVariable Integer id) {
		return clienteRepository.findById(id).orElseThrow(notFound("Cliente não encontrado"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save(@RequestBody Cliente cliente) {
//		cliente = clienteRepository.save(cliente);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
//				.toUri();
//		return ResponseEntity.created(uri).body(cliente);
		return clienteRepository.save(cliente);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		Cliente c = clienteRepository.findById(id).orElseThrow(notFound("Cliente não encontrado"));
		clienteRepository.delete(c);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
		Cliente c = clienteRepository.findById(id).orElseThrow(notFound("Cliente não encontrado"));
		atualizarDados(c, clienteAtualizado);
		clienteRepository.save(c);
	}

	@GetMapping("/matching")
	public List<Cliente> findMatching(Cliente cliente) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example<Cliente> example = Example.of(cliente, matcher);
		return clienteRepository.findAll(example);
	}
	
	@GetMapping("/sample")
	public Cliente sample() {
		return new Cliente();
	}

	private void atualizarDados(Cliente cliente, Cliente clienteAtualizado) {
		cliente.setNome(clienteAtualizado.getNome());
	}

}
