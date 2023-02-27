package udemy.bruno.expert.rest.controller;

import static udemy.bruno.expert.rest.controller.ResponseStatusExceptionSupplier.notFound;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import udemy.bruno.expert.domain.entities.Produto;
import udemy.bruno.expert.domain.repositories.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	@GetMapping("/{id}")
	public Produto findById(@PathVariable Integer id) {
		return produtoRepository.findById(id).orElseThrow(notFound("Produto não encontrado"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto save(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@PostMapping("/many")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Produto> saveAll(@RequestBody List<Produto> produtos) {
		return produtoRepository.saveAll(produtos);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		Produto p = produtoRepository.findById(id).orElseThrow(notFound("Produto não encontrado"));
		produtoRepository.delete(p);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody Produto produtoAtualizado) {
		Produto p = produtoRepository.findById(id).orElseThrow(notFound("Produto não encontrado"));
		atualizarDados(p, produtoAtualizado);
		produtoRepository.save(p);
	}

	@GetMapping("/sample")
	public Produto sample() {
		return new Produto();
	}
	
	private void atualizarDados(Produto produto, Produto produtoAtualizado) {
		produto.setDescricao(produtoAtualizado.getDescricao());
		produto.setPreco(produtoAtualizado.getPreco());
	}

}
