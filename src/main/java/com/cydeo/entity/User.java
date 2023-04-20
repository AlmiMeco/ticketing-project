package com.cydeo.entity;

import com.cydeo.enums.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
@Where(clause = "is_deleted=false") // <- This clause is added to ALL methods(services) which deal with "USER"
public class User extends BaseEntity {


    private String firstName, lastName, userName, passWord, phone;

    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;


}
