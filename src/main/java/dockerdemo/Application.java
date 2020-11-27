package dockerdemo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;


@SpringBootApplication
public class Application extends SpringBootServletInitializer
{
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}


	@Bean
	public DataSource dataSource() throws NamingException
	{
		JndiTemplate jndi = new JndiTemplate();
		return jndi.lookup("java:comp/env/jdbc/Test_DB", DataSource.class);
	}


	@Bean
	public CommandLineRunner createDatabaseTable(JdbcTemplate jdbcTemplate)
	{
		return args -> {
			jdbcTemplate.update(""
					+ "CREATE TABLE PERSON "
					+ "("
					+ "    ID         INT           NOT NULL   IDENTITY(1, 1),"
					+ "    USERNAME   VARCHAR(20)   NOT NULL,"
					+ "    PRIMARY KEY (ID)"
					+ ");");
			jdbcTemplate.update("INSERT INTO PERSON (ID, USERNAME) VALUES (1, 'johan');");
			jdbcTemplate.update("INSERT INTO PERSON (ID, USERNAME) VALUES (2, 'jenny');");
		};
	}
}
