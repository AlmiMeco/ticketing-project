package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.entity.User;

import java.util.List;

public interface TaskService {

    void save(TaskDTO dto);

    List<TaskDTO> listAllTasks();

    TaskDTO findById(Long id);

    void update(TaskDTO dto);

    void delete(Long id);

    int getUnfinishedTaskCount(String projectCode);

    int getFinishedTaskCount(String projectCode);

    void deleteByProject(ProjectDTO dto);

}
