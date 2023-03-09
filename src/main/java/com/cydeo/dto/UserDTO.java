package com.cydeo.dto;

import com.cydeo.enums.Gender;
import com.cydeo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String firstName, lastName, userName, passWord, phone;

    private boolean enabled;

    private Role role;

    private Gender gender;


}
