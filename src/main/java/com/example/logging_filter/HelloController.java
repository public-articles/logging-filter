package com.example.logging_filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class HelloController {

    //private final LoggingDebugAllowedConfiguration loggingDebugAllowedConfiguration;

    @GetMapping("/hello")
    public String sayHello(@RequestParam(defaultValue = "John Doe") String name) {
        log.debug("Saying hello to {}", name);
        return " Hello, " + name + "!";
    }

}
