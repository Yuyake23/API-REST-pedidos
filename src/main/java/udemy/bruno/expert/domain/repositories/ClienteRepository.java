package udemy.bruno.expert.domain.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import udemy.bruno.expert.domain.entities.Cliente;

@Repository
public class ClienteRepository {

	protected static final String SELECT_ALL = "SELECT * FROM CLIENTE";
	protected static final String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?)";
	protected static final String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID_CLIENTE = ?";
	protected static final String DELETE = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Cliente salvar(Cliente obj) {
		jdbcTemplate.update(INSERT, obj.getNome());
		return obj;
	}

	public Cliente atualizar(Cliente obj) {
		jdbcTemplate.update(UPDATE, obj.getNome(), obj.getId());
		return obj;
	}

	public void deletar(Cliente obj) {
		deletar(obj.getId());
	}

	public void deletar(Integer id) {
		jdbcTemplate.update(DELETE, id);
	}

	public List<Cliente> buscarPorNome(String nome) {
		return jdbcTemplate.query(SELECT_ALL + " where nome like ?", ClienteRepository::clienteMapper,
				"%" + nome + "%");
	}

	public List<Cliente> obterTodos() {
		return jdbcTemplate.query(SELECT_ALL, ClienteRepository::clienteMapper);
	}

	private static Cliente clienteMapper(ResultSet rs, int i) throws SQLException {
		return new Cliente(rs.getInt("id_cliente"), rs.getString("nome"));
	}

}
