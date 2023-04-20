package com.cydeo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass

// Behind the scenes fields (NOT visible to end user)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long insertUserId;
    private Long lastUpdateUserId;

    private LocalDateTime insertDateTime;
    private LocalDateTime lastUpdateDateTime;

//    -> Used to differentiate if User will be displayed in Front-End or not
    private Boolean isDeleted = false;


                            /* Used to initiate (hidden) fields */

//    Executed upon User creation
    @PrePersist
    private void onPrePersist(){
        this.insertDateTime = LocalDateTime.now();
        this.lastUpdateDateTime = LocalDateTime.now();
        this.insertUserId = 1L;
        this.lastUpdateUserId = 1L;
    }

//    Executed upon User update
    @PreUpdate
    private void onPreUpdate(){
        this.lastUpdateDateTime = LocalDateTime.now();
        this.lastUpdateUserId = 1L;
    }

}
