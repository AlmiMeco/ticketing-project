package com.cydeo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BaseEntity {

    private Long id;
    private Long insertUserId;
    private Long lastUpdateUserId;

    private LocalDateTime insertDateTime;
    private LocalDateTime lastUpdateDateTime;

}
