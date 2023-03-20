package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String> implements ProjectService {

    /** Dependency Injection **/
//----------------------------------------------------------------------------------------------------------------------
    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }
//----------------------------------------------------------------------------------------------------------------------

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

        if (projectDTO.getProjectStatus() == null) {
            projectDTO.setProjectStatus(findById(projectDTO.getProjectCode()).getProjectStatus());
//            when updating a project -> set the new projectStatus (updated project) to the same as before
        }

        super.update(projectDTO.getProjectCode(), projectDTO);
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void completeProject(ProjectDTO project) {

        project.setProjectStatus(Status.COMPLETED);

    }

    @Override
    public List<ProjectDTO> getCountedListOfTasks(UserDTO manager) {


        return findAll()
                        .stream()
                        .filter(projectDTO -> projectDTO.getAssignedManager().equals(manager))
                        .map(projectDTO -> {

                            /* Because (completedTaskCounts & unfinishedTaskCounts) are not set we are mapping them
                              manually each time (instantiating them) */

                            List<TaskDTO> taskDTOList = taskService.findTasksByManager(manager);

                            int  completedTaskCounts = (int) taskDTOList
                                    .stream()
                                    .filter(t -> t.getProject().equals(projectDTO) && t.getTaskStatus() == Status.COMPLETED)
                                    .count();
                            int unfinishedTaskCounts = (int) taskDTOList
                                    .stream()
                                    .filter(t -> t.getProject().equals(projectDTO) && t.getTaskStatus() != Status.COMPLETED)
                                    .count();

                            projectDTO.setCompletedTaskCounts(completedTaskCounts);
                            projectDTO.setUnfinishedTaskCounts(unfinishedTaskCounts);

                            return projectDTO;

                        })
                        .collect(Collectors.toList());



    }
}
