package udemy.bruno.expert.domain.services.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import udemy.bruno.expert.domain.entities.Cliente;
import udemy.bruno.expert.domain.entities.ItemPedido;
import udemy.bruno.expert.domain.entities.Pedido;
import udemy.bruno.expert.domain.entities.Produto;
import udemy.bruno.expert.domain.enums.StatusPedido;
import udemy.bruno.expert.domain.repositories.ClienteRepository;
import udemy.bruno.expert.domain.repositories.ItemPedidoRepository;
import udemy.bruno.expert.domain.repositories.PedidoRepository;
import udemy.bruno.expert.domain.repositories.ProdutoRepository;
import udemy.bruno.expert.domain.services.PedidoService;
import udemy.bruno.expert.domain.services.exceptions.RegraNegocioException;
import udemy.bruno.expert.exceptions.PedidoNaoEncontradoException;
import udemy.bruno.expert.rest.dto.ItemPedidoDTO;
import udemy.bruno.expert.rest.dto.PedidoDTO;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ClienteRepository clienteRepository;
	private final ProdutoRepository produtoRepository;
	private final ItemPedidoRepository itemPedidoRepository;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getIdCliente();
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));

		Pedido pedido = new Pedido();
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		pedido.setItensPedido(convertItensPedidoDTO(pedido, dto.getItens()));
		BigDecimal total = pedido.getItensPedido().stream()
				.map(ip -> ip.getProduto().getPreco().multiply(new BigDecimal(ip.getQuantidade())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		pedido.setTotal(total);
		pedido.setStatus(StatusPedido.REALIZADO);
		
		pedidoRepository.save(pedido);
		itemPedidoRepository.saveAll(pedido.getItensPedido());
		pedido.getItensPedido().forEach(System.out::println);

		return pedido;
	}

	private Set<ItemPedido> convertItensPedidoDTO(Pedido pedido, Set<ItemPedidoDTO> itens) {
		if (itens.isEmpty())
			throw new RegraNegocioException("Não é possível realizar um pedido sem itens");

		return itens.stream().map((dto) -> {
			Integer idProduto = dto.getIdProduto();
			Produto produto = produtoRepository.findById(idProduto).orElseThrow(
					() -> new RegraNegocioException("Código de produto inválido (%d)"
							.formatted(idProduto)));

			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(dto.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);

			return itemPedido;
		}).collect(Collectors.toSet());
	}

	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		return pedidoRepository.findByIdFetchItensPedido(id);
	}

	@Override
	public void atualizaStatus(Integer id, StatusPedido status) {
		pedidoRepository.findById(id)
		.map(p -> {
			p.setStatus(status);
			return pedidoRepository.save(p);
		}).orElseThrow(() -> new PedidoNaoEncontradoException());
	}

}
