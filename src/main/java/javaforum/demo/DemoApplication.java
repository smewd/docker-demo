package javaforum.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class DemoApplication extends SpringBootServletInitializer {

	@Value("${string.value}")
	private String stringValue;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}	


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}	


    @GetMapping("/stringvalue")
    public String getStringValue() {
        return stringValue;
    }
}
