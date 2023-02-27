package udemy.bruno.expert.exceptions;

import java.io.Serial;

public class PedidoNaoEncontradoException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado");
	}
}
