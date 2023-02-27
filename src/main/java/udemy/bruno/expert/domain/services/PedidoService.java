package udemy.bruno.expert.domain.services;

import java.util.Optional;

import udemy.bruno.expert.domain.entities.Pedido;
import udemy.bruno.expert.domain.enums.StatusPedido;
import udemy.bruno.expert.rest.dto.PedidoDTO;

public interface PedidoService {

	Pedido salvar(PedidoDTO dto);

	Optional<Pedido> obterPedidoCompleto(Integer id);
	
	void atualizaStatus(Integer id, StatusPedido status);
}
