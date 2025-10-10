package javaforum.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
    
    @Value("${string.value}")
    private String stringValue;


    @GetMapping("/stringvalue")
    public String getStringValue() {
        return stringValue;
    }
}
