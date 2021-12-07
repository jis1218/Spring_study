package com.example.clientservice2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
@Slf4j
public class ClientController {

    @GetMapping("/hello2")
    public String hello() {
        return "hello2";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        log.info(header);
        return "second service";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, this is check second service";
    }
}
