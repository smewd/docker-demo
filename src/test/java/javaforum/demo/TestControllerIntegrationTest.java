package javaforum.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestControllerIntegrationTest {

	@LocalServerPort
	int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void getData_returnsMyData_withConfiguredValues() {
		String url = "http://localhost:" + port + "/data";
		ResponseEntity<Map<String, Object>> response = getResponse(url);
		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

		Map<String, Object> body = response.getBody();
		assertThat(body).isNotNull();
		assertThat(body).containsKeys("applicationName", "environmentName", "stringValue");

		// values come from application.properties (defaults bundled in
		// src/main/resources)
		assertThat(body.get("applicationName")).isEqualTo("demo");
		assertThat(body.get("stringValue")).isNotNull();

		String[] profiles = System.getProperty("spring.profiles.active", "").split(",");
		if (profiles[0].equals("prod")) {
			assertProfile(body, "Produktion");
		} else if (profiles[0].equals("dev")) {
			assertProfile(body, "Utveckling");
		} else {
			assertProfile(body, "Ingen profil satt");
		}
	}

	private ResponseEntity<Map<String, Object>> getResponse(String url) {
		return restTemplate.exchange(
				url,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Map<String, Object>>() {
				});
	}

	private void assertProfile(Map<String, Object> body, String name) {
		assertThat(body.get("environmentName")).isEqualTo(name);
	}
}
