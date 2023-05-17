package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserService userService;
    private final UserMapper userMapper;
    private final TaskService taskService;


    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, @Lazy UserService userService, UserMapper userMapper, TaskService taskService) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userService = userService;
        this.userMapper = userMapper;
        this.taskService = taskService;
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

        Project project = projectRepository.findByProjectCode(code);
        return projectMapper.convertToDto(project);

    }

    @Override
    public void save(ProjectDTO dto) {

        // fixes whitelabel error '500' -> "project.projectStatus.value" (template: "project/create" - line 135, col 53)
        dto.setProjectStatus(Status.OPEN);

        projectRepository.save(projectMapper.convertToEntity(dto));

    }

    @Override
    public void update(ProjectDTO dto) {


        Project project = projectRepository.findByProjectCode(dto.getProjectCode());

        Project convertedProject = projectMapper.convertToEntity(dto);

        convertedProject.setId(project.getId());

        convertedProject.setProjectStatus(project.getProjectStatus());

        projectRepository.save(convertedProject);



    }

    @Override
    public void delete(String projectCode) {

        Project project = projectRepository.findByProjectCode(projectCode);
        project.setIsDeleted(true);

        project.setProjectCode(project.getProjectCode() + "--" + project.getId()); // SP00 ... -> ... SP00--01

        projectRepository.save(project);

        taskService.deleteByProject(projectMapper.convertToDto(project));

    }

    @Override
    public void complete(String projectCode) {

        Project project = projectRepository.findByProjectCode(projectCode);

        project.setProjectStatus(Status.COMPLETE);

        projectRepository.save(project);

        taskService.completeByProject(projectMapper.convertToDto(project));


    }

    @Override
    public List<ProjectDTO> listAllProjectDetails() {

        //                                                     vvv Manager(User) (simulating a manger signing-in)
        UserDTO managerDTO = userService.findByUserName("harold@manager.com");
        // <- added bc we need a 'manager'  (no log-in feature) -> will be fixed when we add security
        User managerEntity = userMapper.convertToEntity(managerDTO);

        List<Project> projectsBelongingToManager = projectRepository.findAllByAssignedManager(managerEntity);

        return projectsBelongingToManager.stream()
                .map(project -> {
                    ProjectDTO dto = projectMapper.convertToDto(project);
                    dto.setUnfinishedTaskCounts(taskService.getUnfinishedTaskCount(project.getProjectCode())); // {field in DTO ; but NOT Entity}
                    dto.setCompletedTaskCounts(taskService.getFinishedTaskCount(project.getProjectCode()));  // {field in DTO ; but NOT Entity}
                    return dto;
                })
                .collect(Collectors.toList());

    }
}
