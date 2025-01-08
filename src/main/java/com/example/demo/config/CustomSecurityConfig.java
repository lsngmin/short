package com.example.demo.config;

import com.example.demo.security.filter.JWTCheckFilter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
@Log4j2
@EnableMethodSecurity
public class CustomSecurityConfig {
    private JWTCheckFilter jwtCheckFilter;

    @Autowired
    private void setJwtCheckFilter(JWTCheckFilter jwtCheckFilter) {
        this.jwtCheckFilter = jwtCheckFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(AbstractHttpConfigurer::disable);
        http.logout(config-> config.disable());
        http.csrf(config-> config.disable());
        http.sessionManagement(sessionManagementConfigurer -> {
            sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.NEVER);
        });
        http.addFilterBefore(jwtCheckFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
//                http.authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/list", "/login", "api/v1/signup", "api/v1/user/**", "/error", "/*", "api/v1/token/make").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin((auth) -> auth
//                        .loginPage("/login")
//                        .loginProcessingUrl("/loginProcess")
//                        .defaultSuccessUrl("/")
//                        .permitAll()
//                )
//                .csrf((auth) -> auth.disable())
//                .build();

    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
