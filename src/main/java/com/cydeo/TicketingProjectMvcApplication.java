package com.cydeo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication // < includes @Config
public class TicketingProjectMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketingProjectMvcApplication.class, args);
    }


    // crating bean for ModelMapper (conversion) {Role -> RoleDTO}
        // @Bean -> we cannot use @Component bc ModelMapper is not our local class (Read Only)
    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }

    @Bean // Bean instead of @Component bc we do not have access to this Class (PasswordEncoder class is -> RO)
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }


}
