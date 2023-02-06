package udemy.bruno.expert.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import udemy.bruno.expert.domain.entities.Cliente;
import udemy.bruno.expert.domain.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	List<Pedido> findByCliente(Cliente cliente);
	
}
