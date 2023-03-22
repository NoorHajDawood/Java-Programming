package il.ac.shenkar.spring.demo.hellospring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping
    public String helloWorld() {
        return "Hello World from Spring Boot";
    }
}
