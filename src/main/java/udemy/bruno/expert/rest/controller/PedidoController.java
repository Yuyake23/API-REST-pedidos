package udemy.bruno.expert.rest.controller;

import static udemy.bruno.expert.rest.controller.ResponseStatusExceptionSupplier.notFound;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import udemy.bruno.expert.domain.entities.ItemPedido;
import udemy.bruno.expert.domain.entities.Pedido;
import udemy.bruno.expert.domain.services.PedidoService;
import udemy.bruno.expert.rest.dto.ItemPedidoInfoDTO;
import udemy.bruno.expert.rest.dto.PedidoDTO;
import udemy.bruno.expert.rest.dto.PedidoInfoDTO;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido save(@RequestBody PedidoDTO dto) {
		return service.salvar(dto);
	}

	@GetMapping("/{id}")
	public PedidoInfoDTO getById(@PathVariable Integer id) {
		return service.obterPedidoCompleto(id).map(this::convert).orElseThrow(notFound("Produto não encontrado"));
	}

	private PedidoInfoDTO convert(Pedido pedido) {
		return PedidoInfoDTO.builder()
				.id(pedido.getId())
				.dataPedido(pedido.getDataPedido()
						.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				.cpf(pedido.getCliente().getCpf())
				.nomeCliente(pedido.getCliente().getNome())
				.total(pedido.getTotal())
				.itens(convert(pedido.getItensPedido()))
				.build();
	}

	private Set<ItemPedidoInfoDTO> convert(Set<ItemPedido> itens) {
		if(itens.isEmpty())
			return Collections.emptySet();
		
		return itens.stream().map(item -> ItemPedidoInfoDTO
				.builder()
				.descricao(item.getProduto().getDescricao())
				.precoUnitario(item.getProduto().getPreco())
				.quantidade(item.getQuantidade())
				.build()).collect(Collectors.toSet());
	}
}
