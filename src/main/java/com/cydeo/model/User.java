package com.cydeo.model;

import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class User extends BaseEntity{


    private String firstName, lastName, userName, passWord, phone;

    private boolean enabled;

    private Role role;

    private Gender gender;

    public User(Long id, Long insertUserId, Long lastUpdateUserId, LocalDateTime insertDateTime, LocalDateTime lastUpdateDateTime, String firstName, String lastName, String userName, String passWord, String phone, boolean enabled, Role role, Gender gender) {
        super(id, insertUserId, lastUpdateUserId, insertDateTime, lastUpdateDateTime);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.enabled = enabled;
        this.role = role;
        this.gender = gender;
    }
}
