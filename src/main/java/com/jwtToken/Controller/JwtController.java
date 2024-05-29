package com.jwtToken.Controller;

import com.jwtToken.Model.AuthenticateUser;
import com.jwtToken.Model.AuthenticationResponse;
import com.jwtToken.Model.RegisterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/V1/auth")
@RequiredArgsConstructor
public class JwtController {

    @PostMapping("registerUser")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterUser registerUser){

        return null;
    }
    @PostMapping("authenticateUser")
    public ResponseEntity<AuthenticationResponse>registerUser(@RequestBody AuthenticateUser authenticateUser){

        return null;
    }
}
