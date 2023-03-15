package com.cydeo.dto;

import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private String projectName, projectCode;

    private UserDTO assignedManager;

    private LocalDate startDate, endDate;

    private String projectDetails;

    private Status projectStatus;


}
