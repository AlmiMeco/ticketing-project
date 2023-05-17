package com.cydeo.service.impl;

import com.cydeo.entity.User;
import com.cydeo.entity.common.UserPrincipal;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /** Taking OUR User (Entity / DB) --converting-> that entity to Spring-Security User
       org.springframework.security.core.userdetails **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // getting 'User' (entity pkg) from dB
       User user = userRepository.findByUserName(username);


       if (user == null) throw new UsernameNotFoundException(username);

       // return new UserPrincipal (UserPrincipal -> maps entity 'User' to Security 'User')
       return new UserPrincipal(user);
    }

}
