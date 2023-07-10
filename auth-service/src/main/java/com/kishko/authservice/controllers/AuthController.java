package com.kishko.authservice.controllers;
import com.kishko.authservice.entities.AuthenticationRequest;
import com.kishko.authservice.entities.AuthenticationResponse;
import com.kishko.authservice.entities.RegisterRequest;
import com.kishko.authservice.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Operation(
            description = "Регистрация нового пользователя",
            summary = "Регистрация нового пользователя",
            responses = {
                @ApiResponse(
                        description = "Success",
                        responseCode = "200"
                )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {

        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.CREATED);

    }

    @Operation(
            description = "Аутентификация существующего пользователя",
            summary = "Аутентификация существующего пользователя",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Wrong data",
                            responseCode = "403"
                    )
            }
    )
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authenticationService.authenticate(request));

    }

}
