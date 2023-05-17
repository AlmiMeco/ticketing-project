package com.cydeo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners((BaseEntity.class))

// Behind the scenes fields (NOT visible to end user)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private Long insertUserId;
    @Column(nullable = false, updatable = false)
    private LocalDateTime insertDateTime;
    @Column(nullable = false)
    private Long lastUpdateUserId;
    @Column(nullable = false)
    private LocalDateTime lastUpdateDateTime;

//    -> Used to differentiate if User will be displayed in Front-End or not
    private Boolean isDeleted = false;




}
