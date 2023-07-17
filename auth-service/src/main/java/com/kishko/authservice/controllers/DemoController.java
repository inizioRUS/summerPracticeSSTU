package com.kishko.authservice.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/demo-controller")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin
@Hidden
public class DemoController {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<>("Hello from secured endpoint", HttpStatus.OK);
    }

}
