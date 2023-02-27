package udemy.bruno.expert.rest;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {

	@Getter
	private List<String> errors;

	public ApiErrors(String... erros) {
		this.errors = Arrays.asList(erros);
	}

}
