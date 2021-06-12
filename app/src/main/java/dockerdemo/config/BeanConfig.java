package dockerdemo.config;


import dockerdemo.components.HelloComponent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
@ComponentScan(basePackageClasses = {
		HelloComponent.class
})
public class BeanConfig
{
	@PostConstruct
	public void setup()
	{
		System.out.println("\n\nPost-init BeanConfig\n\n");
	}
}
