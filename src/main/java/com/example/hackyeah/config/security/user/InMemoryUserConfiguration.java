package com.example.hackyeah.config.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class InMemoryUserConfiguration {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InMemoryUserConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password(passwordEncoder.encode("password"))
                .roles("USER").build());
        manager.createUser(User.withUsername("admin").password(passwordEncoder.encode("admin"))
                .roles("USER","ADMIN").build());
        return manager;
    }
}
