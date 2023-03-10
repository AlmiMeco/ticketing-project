package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    UserDTO findById(String userName);

    List<UserDTO> findAll();

    void deleteById (String userName);



}
