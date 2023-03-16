package com.cydeo.dto;

import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private Long taskID; // hidden ID to the end-user (added to make each Task unique)
                      //  --> in the actual work environment dataBase will assign a unique ID automatically (primaryKey)

    private ProjectDTO project;

    private UserDTO assignedEmployee;

    private String taskSubject, taskDetail;

    private Status taskStatus;

    private LocalDate assignedDate;

}
