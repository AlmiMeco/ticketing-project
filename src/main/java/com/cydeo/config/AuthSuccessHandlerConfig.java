package com.cydeo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Configuration
public class AuthSuccessHandlerConfig implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

//        retrieving a Users authority (Role)
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        // if user -> Admin redirect user/create
        if (roles.contains("Admin")) {
            response.sendRedirect("/user/create");
        }

        if (roles.contains("Manager")) {
            response.sendRedirect("/task/create");
        }

        if (roles.contains("Employee")) {
            response.sendRedirect("/task/employee");
        }

    }
}
