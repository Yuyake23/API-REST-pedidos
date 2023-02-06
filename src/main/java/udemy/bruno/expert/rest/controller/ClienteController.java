package udemy.bruno.expert.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import udemy.bruno.expert.domain.entities.Cliente;
import udemy.bruno.expert.domain.repositories.ClienteRepository;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
		return ResponseEntity.of(clienteRepository.findById(id));	
	}

}
