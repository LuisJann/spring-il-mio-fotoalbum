package com.example.springilmiofotoalbum.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    UserDetailsService userDetailsService(){
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/categories", "/categories/**").hasAuthority("ADMIN")
                .requestMatchers("/messages", "/messages/**").hasAuthority("ADMIN")
                .requestMatchers("/photos/create", "/photos/edit/**", "/photos/delete/**").hasAuthority("ADMIN")
                .requestMatchers("/", "/photos", "/photos/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST, "/photos/**").hasAuthority("ADMIN")
                .requestMatchers("/**").permitAll()
                .and().formLogin()
                .and().logout()
                .and().exceptionHandling();
        // debug
        http.csrf().disable();
        return http.build();
    }
}
