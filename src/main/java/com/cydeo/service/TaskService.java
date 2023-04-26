package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;

import java.util.List;

public interface TaskService {

    void save(TaskDTO dto);

    List<TaskDTO> listAllTasks();

    TaskDTO findById(Long id);

    void update(TaskDTO dto);

    void delete(Long id);

//    void complete(Long id);

}
