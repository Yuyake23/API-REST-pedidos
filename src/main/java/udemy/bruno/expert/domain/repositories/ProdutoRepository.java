package udemy.bruno.expert.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import udemy.bruno.expert.domain.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
