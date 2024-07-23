package com.futureboost.FutureBoostify.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenBlacklistService tokenBlacklistService;

    /*
     * intercept every request and extract data for example
     * from the request and provide new data within the response
     *FilterChain is the chain of responsibility design patterns
     * it will contains list of the other filters that we need to execute
     *
     * */
    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwtToken);
        // check if user is auth
        // if is not
        // we take our userdetails from the db
        if (tokenBlacklistService.isBlacklisted(jwtToken)) {
             ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is blacklisted");
             return;
        }
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null
        && !tokenBlacklistService.isBlacklisted(authHeader)) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            // check is user and token are valid
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                // Extract cookie from request headers
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ("JWT".equals(cookie.getName())) {
                            // Check if cookie is expired
                            if (cookie.getMaxAge() == 0) {
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                response.getWriter().write("Cookie has expired");
                                return;
                            }
                            break;
                        }
                    }
                }
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                // update auth token
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }
        filterChain.doFilter(request, response);
    }

}
