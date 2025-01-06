package com.chocksaway.imager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {
    //LDAP or Database

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        return new InMemoryUserDetailsManager(getUserDetails());
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
auth -> auth.anyRequest().authenticated() );
        http.formLogin(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }

    private static List<UserDetails> getUserDetails() {
        Function<String, String> passwordEncoder
                = input -> passwordEncoder().encode(input);

        return List.of(
            User.builder()
                .passwordEncoder(passwordEncoder)
                .username("milesd")
                .password("milesd")
                .roles("USER", "ADMIN")
                .build(),
            User.builder()
                .passwordEncoder(passwordEncoder)
                .username("milesd2")
                .password("milesd2")
                .roles("USER", "ADMIN")
                .build()
        );
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static String getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


}
