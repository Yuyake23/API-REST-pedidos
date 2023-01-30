package udemy.bruno.expert.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainResource {

	@Autowired
	@Qualifier("mainView")
	private String mainView;

	@GetMapping("/main")
	public ResponseEntity<String> helloWorld() {
		return ResponseEntity.ok(mainView);
	}

}
