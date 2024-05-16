package com.example.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private UserConfig userDetailsService;

    private final JWTUtil jwtUtil = new JWTUtil();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // String token = authorizationHeader.substring(7, authorizationHeader.length());
            String token = authorizationHeader.substring(7);
            log.info("Token: {}", token);

            if (jwtUtil.validateAccessToken(token)) {
                String username = jwtUtil.getClaim(token, "sub", String.class);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.getAuthorities().stream().forEach(authority -> log.info("Authority: {}", authority.getAuthority()));
                authenticationToken.setDetails(userDetails);
                log.info("UserDetails: {}", authenticationToken.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
