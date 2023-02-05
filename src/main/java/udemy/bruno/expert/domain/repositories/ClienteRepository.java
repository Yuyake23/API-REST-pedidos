package udemy.bruno.expert.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import udemy.bruno.expert.domain.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByNomeLike(String string);

	List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

	Cliente findOneById(Integer id);

	boolean existsByNome(String nome);

}