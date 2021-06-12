package dockerdemo.components;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class HelloComponent
{
	public String sayHello()
	{
		return String.format("hello, %s", LocalDateTime.now());
	}
}
