package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        //http.authorizeHttpRequests().anyRequest().permitAll();


        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);


        /*http.authorizeHttpRequests().requestMatchers(
                "/api/v1/auth/admin-manager/signUp",
                "/api/v1/auth/content-manager/signUp",
                "/api/v1/auth/blog-manager/signUp",
                "/api/v1/auth/signIn",
                "/api/v1/auth/generate-otp",
                "/api/v1/auth/validate-otp",
                "/api/second-hand-car/add-car")

                .permitAll().requestMatchers("/api/v2/car/add-car")
                .hasAnyRole("ADMIN")
                .anyRequest().authenticated();

       // http.authorizeHttpRequests().anyRequest().permitAll();

        return http.build();*/
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/second-hand-car/add-car").permitAll()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v2/car/add-car").hasRole("ADMIN")
                .anyRequest().authenticated();

        return http.build();


    }
}
