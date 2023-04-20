package com.cydeo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass

public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long insertUserId;
    private Long lastUpdateUserId;

    private LocalDateTime insertDateTime;
    private LocalDateTime lastUpdateDateTime;

    private Boolean isDeleted = false;

}
