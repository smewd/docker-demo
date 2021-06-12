package dockerdemo.app.config;


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
		System.out.printf("%n%nPost-init %s%n%n", getClass().getName());
	}
}
