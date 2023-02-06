package udemy.bruno.expert.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import udemy.bruno.expert.domain.entities.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
