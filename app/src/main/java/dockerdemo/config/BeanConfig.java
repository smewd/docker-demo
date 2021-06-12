package dockerdemo.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackageClasses = {
		dockerdemo.components.HelloComponent.class
})
public class BeanConfig
{
}
