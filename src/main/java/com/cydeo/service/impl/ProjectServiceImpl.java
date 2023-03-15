package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String> implements ProjectService {


    @Override
    public ProjectDTO save(ProjectDTO projectDTO) {

        if (projectDTO.getProjectStatus() == null) {projectDTO.setProjectStatus(Status.OPEN);}

        return super.save(projectDTO.getProjectCode(), projectDTO);
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO projectDTO) {
        super.update(projectDTO.getProjectCode(), projectDTO);
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }
}
