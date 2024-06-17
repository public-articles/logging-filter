package com.example.logging_filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(defaultValue = "John Doe") String name) {
        log.debug("Saying hello to {}", name);
        return "Hello, " + name + "!";
    }

}
