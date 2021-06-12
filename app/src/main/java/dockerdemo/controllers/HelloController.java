package dockerdemo.controllers;


import dockerdemo.components.HelloComponent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;


@Controller
@RequestMapping("/hello")
public class HelloController
{
	private final HelloComponent helloComponent;


	public HelloController(HelloComponent helloComponent)
	{
		this.helloComponent = helloComponent;
	}


	@PostConstruct
	public void setup()
	{
		System.out.printf("%n%nPost-init %s%n%n", getClass().getName());
	}


	@GetMapping
	@ResponseBody
	public String hello()
	{
		return helloComponent.sayHello();
	}
}
