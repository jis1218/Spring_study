package com.insup.user.user.controller;

import com.insup.user.user.dto.UserRequest;
import com.insup.user.user.dto.UserResponse;
import com.insup.user.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Value("${greeting.welcome}")
    String welcomeMessage;

    @GetMapping("/health_check")
    public String status() {
        return "It's working in user service";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return welcomeMessage;
    }

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody UserRequest userRequest) {

        UserResponse userResponse = userService.createUser(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/")
    public ResponseEntity findAllUser() {
        List<UserResponse> userResponses = userService.findAllUser();

        return ResponseEntity.status(HttpStatus.OK).body(userResponses);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity findUser(@PathVariable Long userId) {
        UserResponse userResponse = userService.findUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
}
