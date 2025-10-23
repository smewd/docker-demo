package javaforum.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class TestControllerIntegrationTest {

	@LocalServerPort
	int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private Environment environment;


	@Test
	void getData_returnsMyData_withConfiguredValues() {
		String url = "http://localhost:" + port + "/data";
		ResponseEntity<Map<String, Object>> response = getResponse(url);
		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

		Map<String, Object> body = response.getBody();
		assertNotNull(body);
		assertThat(body).containsKeys("applicationName", "environmentName", "stringValue");

		// values come from application.properties (defaults bundled in src/main/resources)
		assertThat(body.get("applicationName")).isEqualTo("demo");
		assertThat(body.get("stringValue")).isEqualTo("Hejsan application-dev.properties");

		String profile = environment.getActiveProfiles()[0];
		assertThat(profile).isEqualTo("dev");

		// values come from application-dev.properties or application-prod.properties depending on active profile
		if (profile.equals("prod")) {
			assertProfile(body, "Produktion");
		} else if (profile.equals("dev")) {
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
