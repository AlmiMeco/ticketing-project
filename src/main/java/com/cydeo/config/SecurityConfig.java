package com.cydeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {


//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//
//        List<UserDetails> userDetailsList = new ArrayList<>();
//
//        userDetailsList.add(
//                new User("mike", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")))
//        );
//
//        userDetailsList.add(
//                new User("jill", encoder.encode("password2"), Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER")))
//        );
//
//
//        return new InMemoryUserDetailsManager(userDetailsList);
//
//    }

    /**
     * FILTERING WHO (AUTHENTICATING) ......... CAN ACCESS WHICH PAGES (AUTHORIZING)
     **/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeRequests()
                .antMatchers("/task/**").hasAuthority("Manager")
                .antMatchers("/task/employee/**").hasAuthority("Employee")
                .antMatchers("/project/**").hasAuthority("Manager")
                .antMatchers("/user/**").hasAuthority("Admin")
                .antMatchers(
                        "/",
                        "/login",
                        "/fragments/**",
                        "/assets/**",
                        "/images/**"
                ).permitAll() //allow any User to enter ^^ given end-points
                .anyRequest().authenticated()
                .and()
//                .httpBasic() // (default)style of our login form
                    .formLogin()
                    .loginPage("/login") // (custom)style of our login form
                    .defaultSuccessUrl("/welcome") // succseful login -> /welcome page
                    .failureUrl("/login?error=true") // un-succseful login -> /login?error=true query
                    .permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                .and().build();
    }


}
