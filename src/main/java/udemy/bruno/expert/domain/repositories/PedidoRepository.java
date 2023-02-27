package udemy.bruno.expert.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import udemy.bruno.expert.domain.entities.Cliente;
import udemy.bruno.expert.domain.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	List<Pedido> findByCliente(Cliente cliente);

	@Query("select p from Pedido p left join fetch p.itensPedido where p.id = :id")
	Optional<Pedido> findByIdFetchItensPedido(@Param("id") Integer id);
}
