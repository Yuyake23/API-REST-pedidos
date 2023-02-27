package udemy.bruno.expert.rest.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {
	
	private Integer idCliente;
	private Set<ItemPedidoDTO> itens;
	
}
