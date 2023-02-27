package udemy.bruno.expert.domain.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemPedido implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_item_pedido")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_pedido")
	@JsonIgnore
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	private Integer quantidade;

	@Override
	public int hashCode() {
		return Objects.hash(id, pedido, produto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id) && Objects.equals(pedido, other.pedido)
				&& Objects.equals(produto, other.produto);
	}

	@Override
	public String toString() {
		return "ItemPedido [id=" + id + ", idPedido=" + pedido.getId() + ", descricaoProduto=" + produto.getDescricao()
				+ ", quantidade=" + quantidade + "]";
	}
	
}
