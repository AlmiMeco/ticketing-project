package com.cydeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {


    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){

        List<UserDetails> userDetailsList = new ArrayList<>();

        userDetailsList.add(
                new User("mike", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")))
        );

        userDetailsList.add(
                new User("jill", encoder.encode("password2"), Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER")))
        );



        return new InMemoryUserDetailsManager(userDetailsList);

    }

}
