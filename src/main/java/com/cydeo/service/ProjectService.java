package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProjectService {

    List<ProjectDTO> listAllProjects();

    ProjectDTO getByProjectCode(String code);

    void save(ProjectDTO dto);

    void update(ProjectDTO dto);

    void delete(String projectCode);

    void complete(String projectCode);


}
