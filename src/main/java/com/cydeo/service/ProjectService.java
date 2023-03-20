package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;

import java.util.List;

public interface ProjectService extends CRUDService<ProjectDTO, String> {

    void completeProject(ProjectDTO project);

    List<ProjectDTO> getCountedListOfTasks(UserDTO manager);

}
