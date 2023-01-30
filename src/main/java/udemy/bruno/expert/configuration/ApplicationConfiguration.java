package udemy.bruno.expert.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

	@Bean("mainView")
	public String mainView() {
		return """
				<h1>Aplicação de Estudos</h1>
				<p>Esta aplicação spring boot foi feita acompanhando o curso Spring Boot Expert disponível na Udemy</p>
				<ul>
					<li>Spring boot</li>
					<li>Spring jpa</li>
					<li>Spring web</li>
					<li>Spring security</li>
				</ul>
				""";
	}

}
