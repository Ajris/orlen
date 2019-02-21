package com.example.hackyeah.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET).hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/findPath").hasRole("USER");
        http.authorizeRequests().antMatchers("/**").hasRole("ADMIN");
        http.csrf().disable();
        http.userDetailsService(userDetailsService);
        http.formLogin().permitAll();
        http.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID");
    }
}
