package udemy.bruno.expert.rest.controller;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResponseStatusExceptionSupplier {

	public static Supplier<? extends ResponseStatusException> notFound(String str) {
		return () -> new ResponseStatusException(HttpStatus.NOT_FOUND, str);
	}
	
}
