package com.example.clientservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/first-service")
@Slf4j
@RequiredArgsConstructor
public class ClientController {
    private final Environment environment;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info(header);
        return "first service";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("server port={}", request.getServerPort());
        return "Hi, this is check first service";
    }
}
