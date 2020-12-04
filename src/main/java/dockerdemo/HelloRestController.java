package dockerdemo;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/hello/rest")
public class HelloRestController
{
	private final JdbcTemplate jdbcTemplate;


	public HelloRestController(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}


	@GetMapping
	public Map<String, Object> hello()
	{
		List<Map<Integer, String>> rows = jdbcTemplate.query(
				"SELECT * FROM PERSON;",
				(rs, i) -> {
					Map<Integer, String> map = new HashMap<>();
					map.put(
							rs.getInt("ID"),
							rs.getString("USERNAME")
					);
					return map;
				});

		Map<String, Object> map = new HashMap<>();
		map.put("users", rows);
		map.put("date/time", LocalDateTime.now());
		return map;
	}
}
