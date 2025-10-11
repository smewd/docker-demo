package javaforum.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
	
	@Value("${spring.application.name}")
	private String applicationName;
	
	@Value("${environment.name}")
	private String environmentName;
	
	@Value("${string.value}")
	private String stringValue;


	@GetMapping("/data")
	public Data getData() {
		return new Data(applicationName, environmentName, stringValue);
	}
}
