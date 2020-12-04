package dockerdemo;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/hello/web")
public class HelloWebController
{
	private final JdbcTemplate jdbcTemplate;


	public HelloWebController(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}


	@GetMapping
	public ModelAndView hello()
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", getPersonsFromDatabase());
		mav.addObject("date_time", LocalDateTime.now());
		mav.setViewName("hello");
		return mav;
	}


	private List<Map<Integer, String>> getPersonsFromDatabase()
	{
		return jdbcTemplate.query(
				"SELECT * FROM PERSON;",
				(rs, i) -> {
					Map<Integer, String> map = new HashMap<>();
					map.put(
							rs.getInt("ID"),
							rs.getString("USERNAME")
					);
					return map;
				});
	}
}
