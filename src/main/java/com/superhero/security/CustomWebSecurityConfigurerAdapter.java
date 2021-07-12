package com.superhero.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    private static final String ADMIN = "ADMIN";
    private static final String PATH_SUPERHERO = "/superhero/**";
    private static final String PATH_H2 = "/h2-console/**";

    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("12345")).roles(ADMIN)
                .and()
                .withUser("user").password(passwordEncoder().encode("12345")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf()
                .ignoringAntMatchers(PATH_SUPERHERO, PATH_H2)
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.DELETE, PATH_SUPERHERO).hasRole(ADMIN)
                .mvcMatchers(HttpMethod.PUT, PATH_SUPERHERO).hasRole(ADMIN)
                .mvcMatchers(HttpMethod.PUT, PATH_SUPERHERO).hasRole("USER")
                .mvcMatchers(HttpMethod.GET, PATH_SUPERHERO).permitAll()
                .mvcMatchers(PATH_H2).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);

        http.addFilterAfter(new CustomFilter(),
                BasicAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
