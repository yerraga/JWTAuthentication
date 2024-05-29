package com.jwtToken.Service;

import com.jwtToken.Entity.User;
import com.jwtToken.Enum.Role;
import com.jwtToken.Model.AuthenticateUser;
import com.jwtToken.Model.AuthenticationResponse;
import com.jwtToken.Model.RegisterUser;
import com.jwtToken.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUserService {
    @Autowired
    UserRepository userRepository;
    JwtService jwtService;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticationResponse(RegisterUser registerUser){
        var user  = User.builder()
                .firstName(registerUser.getFirstName())
                .lastName(registerUser.getLastName())
                .email(registerUser.getEmail())
                .password(passwordEncoder.encode(registerUser.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticateUser(AuthenticateUser authenticateUser){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticateUser.getEmail(), authenticateUser.getPassword())
        );
       String jwtToken;
        try {
            var  user = userRepository.findByEmail(authenticateUser.getEmail());
             jwtToken = jwtService.generateToken(user);
        }
        catch(Exception e){
            throw new UsernameNotFoundException("User not found with the email provided");
        }

        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
