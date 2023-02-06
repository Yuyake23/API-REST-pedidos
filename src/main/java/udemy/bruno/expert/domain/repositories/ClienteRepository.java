package udemy.bruno.expert.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import udemy.bruno.expert.domain.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

//	@Query(value = "select c from Cliente c where c.nome like :param1")
	@Query(value = "select c.id_cliente, c.nome from cliente c where c.nome like '%:param1%'", nativeQuery = true)
	List<Cliente> encontrarPorNome(@Param("param1") String nome);

	@Query(value = "delete from Cliente c where c.nome =:nome")
	@Modifying
	void deletarPorNome(@Param("nome") String nome);

	boolean existsByNome(String nome);

	@Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id  ")
	Cliente findByIdFetchPedidos(@Param("id") Integer id);

}