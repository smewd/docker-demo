package dockerdemo.components;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;


@Component
public class HelloComponent
{
	@PostConstruct
	public void setup()
	{
		System.out.printf("%n%nPost-init %s%n%n", getClass().getName());
	}


	public String sayHello()
	{
		return String.format("hello, %s", LocalDateTime.now());
	}
}
