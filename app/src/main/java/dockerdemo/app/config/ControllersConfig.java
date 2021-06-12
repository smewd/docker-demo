package dockerdemo.app.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackageClasses = dockerdemo.controllers.HelloController.class)
public class ControllersConfig
{
}
