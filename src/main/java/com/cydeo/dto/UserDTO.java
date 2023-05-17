package com.cydeo.dto;

import com.cydeo.entity.Role;
import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

//    Added as foreignKey for (ProjectTable)
    private Long id;

    @NotBlank
    @Size(max = 20, min = 2)
    private String firstName, lastName;

    @NotBlank
    @Email
    private String userName;

    @NotBlank
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}")
    private String passWord;

    private String confirmPassword;

    @NotBlank
    @Pattern(regexp = "\"^\\\\d{10}$\"")
    private String phone;

    private boolean enabled;

    @NotNull // <-- @NotNull bc (@NotBlank is for String only)
    private RoleDTO role;

    @NotNull
    private Gender gender;


}
