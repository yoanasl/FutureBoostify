package com.futureboost.FutureBoostify.auth;

import com.futureboost.FutureBoostify.config.JwtService;
import com.futureboost.FutureBoostify.enums.Role;
import com.futureboost.FutureBoostify.model.User;
import com.futureboost.FutureBoostify.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    //login
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // if username or password is not correct the exception will be thrown
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()));
        User user = repository.findAllByEmail(request.getEmail()).orElseThrow();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();

    }

    // regiter user, save it do db and return generated token
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {

        if (repository.findAllByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("User already exists with email: " + request.getEmail());
        }
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        if (request.getEmail().contains("admin")) {
            user.setRole(Role.ADMIN);
        }

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @PostConstruct
    public void registerAdmin() {
        if (repository.findAllByEmail("admin@abv.bg").isEmpty()) {
            var request = new RegisterRequest("Admin", "Admin", "admin@abv.bg", "admin");
            register(request);
        }
    }

    public User getCurrentUser() {
        // Get the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract the username from the authentication object
        String username = authentication.getName();
        Optional<User> optionalUser = repository.findAllByEmail(username);

        // If user exists, return it. Otherwise, throw an exception.
        return optionalUser.orElseThrow(() -> new IllegalStateException("Current user not found"));
    }
    public AuthenticationResponse logout(String token) {
        String invalidatedToken = jwtService.generateToken(token);
//
//        SecurityContextHolder.clearContext();

        var s = invalidatedToken;
        return AuthenticationResponse.builder()
                .token(invalidatedToken)
                .build();
    }
}




