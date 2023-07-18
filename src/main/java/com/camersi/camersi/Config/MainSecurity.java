package com.camersi.camersi.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.camersi.camersi.Security.jwt.JwtEntryPoint;
import com.camersi.camersi.Security.jwt.JwtTokenFilter;

@Configuration
@EnableWebSecurity
public class MainSecurity {
    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Bean
    JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors((cors) -> cors.disable()).csrf((csrf) -> csrf.disable())
        .authorizeHttpRequests(authorize -> {
            try {
                authorize.requestMatchers("/auth/**","/graphql/**","/graphiql/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated();
            } catch (Exception e) {
                e.printStackTrace();
            }
        })
        
                .exceptionHandling((exceptionHandling) -> exceptionHandling.authenticationEntryPoint(jwtEntryPoint))
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}