package com.cydeo.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

//    private Long id; --> ID (pk) is inherited from BaseEntity
    private String description;


}
