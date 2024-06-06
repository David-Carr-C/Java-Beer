package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.ALWAYS;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserConfig userConfig;

    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        // jwtAuthenticationFilter.setFilterProcessesUrl("/api/login"); // URL de inicio de sesión

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // deshabilita CSRF (Cross-Site Request Forgery)
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(ALWAYS);
                    session.sessionFixation().migrateSession();
                })
                .addFilter(jwtAuthenticationFilter)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/public/**").permitAll();
                    auth.requestMatchers("/api/**").authenticated();
                    auth.requestMatchers("/api/user").hasRole("ADMIN");
                    auth.requestMatchers("/api/user").hasAuthority("ROLE_ADMIN");
                    auth.requestMatchers("/api/user").hasAuthority("ADMIN");
                    auth.requestMatchers("/user").hasRole("ADMIN"); // FALTO QUE LE AGREGUE /api
                    auth.requestMatchers("/api/user").hasAuthority("ADMIN");
                    //auth.anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
        httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userConfig)
                .passwordEncoder(passwordEncoder);

        return httpSecurity
                .getSharedObject(AuthenticationManagerBuilder.class)
                .getOrBuild();
    }
}

/**
 * Se deshabilita CSRF (Cross-Site Request Forgery)
 * para que acepte peticiones desde cualquier origen y admita post, put y delete en Postman.
 *
 * Hay que tener en cuenta que GET no se ve afectado por CSRF.
 * O que entre servidores no se ve afectado por CSRF.
 */