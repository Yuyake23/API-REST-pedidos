package udemy.bruno.expert.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import udemy.bruno.expert.domain.services.exceptions.RegraNegocioException;
import udemy.bruno.expert.exceptions.PedidoNaoEncontradoException;
import udemy.bruno.expert.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleRegraNegocioException(RegraNegocioException e) {
		return new ApiErrors(e.getMessage());
	}

	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handlePedidoNaoEncontradoException(PedidoNaoEncontradoException e) {
		return new ApiErrors(e.getMessage());
	}
}
