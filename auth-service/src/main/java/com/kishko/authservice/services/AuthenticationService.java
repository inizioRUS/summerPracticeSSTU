package com.kishko.authservice.services;

import com.kishko.authservice.entities.AuthenticationRequest;
import com.kishko.authservice.entities.AuthenticationResponse;
import com.kishko.authservice.entities.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
