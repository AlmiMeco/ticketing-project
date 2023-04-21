package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import com.cydeo.enums.Status;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.service.ProjectService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;


    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }



    @Override
    public List<ProjectDTO> listAllProjects() {

        return projectRepository.findAll(Sort.by("projectCode"))
                .stream()
                .map(projectMapper::convertToDto)
                .collect(Collectors.toList());


    }

    @Override
    public ProjectDTO getByProjectCode(String code) {
        return null;
    }

    @Override
    public void save(ProjectDTO dto) {

        // fixes whitelabel error '500' -> "project.projectStatus.value" (template: "project/create" - line 135, col 53)
        dto.setProjectStatus(Status.OPEN);

        projectRepository.save(projectMapper.convertToEntity(dto));

    }

    @Override
    public void update(ProjectDTO dto) {

    }

    @Override
    public void delete(String projectCode) {

        Project project = projectRepository.findByProjectCode(projectCode);

        project.setIsDeleted(true);

        projectRepository.save(project);



    }

    @Override
    public void complete(String projectCode) {

        Project project = projectRepository.findByProjectCode(projectCode);

        project.setProjectStatus(Status.COMPLETED);

        projectRepository.save(project);

    }
}
