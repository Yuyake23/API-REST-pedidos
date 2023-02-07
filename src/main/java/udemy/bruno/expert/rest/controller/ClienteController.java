package udemy.bruno.expert.rest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import udemy.bruno.expert.domain.entities.Cliente;
import udemy.bruno.expert.domain.repositories.ClienteRepository;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Cliente>> findAll() {
		return ResponseEntity.ok(clienteRepository.findAll());
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		return ResponseEntity.of(clienteRepository.findById(id));
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		cliente = clienteRepository.save(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).body(cliente);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		try {
			clienteRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<? super Void> update(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
		return clienteRepository.findById(id).map(c -> {
			atualizarDados(c, clienteAtualizado);
			clienteRepository.save(c);
			return ResponseEntity.noContent().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());

	}

	private void atualizarDados(Cliente cliente, Cliente clienteAtualizado) {
		cliente.setNome(clienteAtualizado.getNome());
	}

}
