package com.futureboost.FutureBoostify.auth;


import com.futureboost.FutureBoostify.model.User;
import com.futureboost.FutureBoostify.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.futureboost.FutureBoostify.config.TokenBlacklistService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final TokenBlacklistService tokenBlacklistService;
    @Autowired
    private UserRepository userRepository;
    @Value("${application.security.jwt.expiration}")
    private Long jwtExpiration;
    @PostMapping("/api/v1/auth/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request, HttpServletResponse servletResponse) {
        try {
            AuthenticationResponse response = authenticationService.register(request);
            setCookieToken(servletResponse, response.getToken(),jwtExpiration);
            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthenticationResponse("User already exists with email: " + request.getEmail()));
        }
    }

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request, HttpServletResponse servletResponse) {
        try {
            AuthenticationResponse response = authenticationService.authenticate(request);
            setCookieToken(servletResponse, response.getToken(),jwtExpiration);
            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthenticationResponse("Invalid credentials "));

        }
    }

//    @PostMapping("/api/v1/auth/logout")
//    public ResponseEntity<AuthenticationResponse> logout(HttpServletRequest request, HttpServletResponse servletResponse) {
//        final String authHeader = request.getHeader("Authorization");
//        ;
//        if (authHeader == null || !authHeader.startsWith("Bearer ") ||
//            tokenBlacklistService.isBlacklisted(authHeader)) {
//            // If there is no Authorization header or it doesn't start with "Bearer ",
//            // you can handle it according to your application's requirements.
//            // For example, you could return a ResponseEntity with an appropriate status code.
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthenticationResponse("Unauthorized"));
//        }
//
//        setCookieToken(servletResponse, "",0);
//        tokenBlacklistService.addToBlacklist(authHeader);
//
//        // Return ResponseEntity indicating successful logout
//        return ResponseEntity.ok(new AuthenticationResponse("Logged out successfully"));
//    }


    private void setCookieToken(HttpServletResponse response, String jwtToken, long maxAge) {
        ResponseCookie cookie = ResponseCookie.from("JWT", jwtToken)
                .httpOnly(true)
                .secure(true) // Enable this if your application is HTTPS only
                .path("/") // Adjust path as needed
                .maxAge(maxAge) // Set cookie expiration time
                .sameSite("Strict") // Adjust this based on your requirements
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            request.logout();
        } catch (Exception e) {
        }

        SecurityContextHolder.clearContext();
    }
    @GetMapping("/api/v1/auth/admin/users")
    public ResponseEntity<List<User>> getAll(){
       return ResponseEntity.ok().body(userRepository.findAll());
    }


}
