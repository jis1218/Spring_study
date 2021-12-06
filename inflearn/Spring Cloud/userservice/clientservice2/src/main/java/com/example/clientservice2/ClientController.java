package com.example.clientservice2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ClientController {

    @GetMapping("/hello2")
    public String hello() {
        return "hello2";
    }
}
