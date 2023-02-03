package udemy.bruno.expert.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import udemy.bruno.expert.annotations.DevProfile;

@Configuration
@DevProfile
public class DevelopmentConfiguration implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("RODANDO CONFIGARAÇÂO - DEVELOPMENT");
	}

}
