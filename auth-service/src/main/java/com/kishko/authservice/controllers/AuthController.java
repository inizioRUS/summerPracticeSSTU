package com.kishko.authservice.controllers;
import com.kishko.authservice.entities.AuthenticationRequest;
import com.kishko.authservice.entities.AuthenticationResponse;
import com.kishko.authservice.entities.RegisterRequest;
import com.kishko.authservice.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {

        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.CREATED);

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authenticationService.authenticate(request));

    }

}
