package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.Task;
import com.cydeo.enums.Status;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.repository.TaskRepository;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectMapper projectMapper;


    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, ProjectMapper projectMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectMapper = projectMapper;
    }


    @Override
    public void save(TaskDTO dto) {

        dto.setTaskStatus(Status.OPEN);
        dto.setAssignedDate(LocalDate.now());

        taskRepository.save(taskMapper.convertToEntity(dto));

    }

    @Override
    public List<TaskDTO> listAllTasks() {

        return taskRepository.findAll().stream()
                .map(taskMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO findById(Long id) {

        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()) {
            return taskMapper.convertToDto(task.get());
        }
        return null;
    }

    @Override
    public void update(TaskDTO dto) {

        Optional<Task> task = taskRepository.findById(dto.getTaskID());
        Task convertedTask = taskMapper.convertToEntity(dto);

        if (task.isPresent()) {
            convertedTask.setTaskStatus(task.get().getTaskStatus());
            convertedTask.setAssignedDate(task.get().getAssignedDate());
            taskRepository.save(convertedTask);
        }


    }

    @Override
    public void delete(Long id) {

//        taskRepository.deleteById(id); // <- this is NOT a soft-delete (UI && dB delete)

        /* Ozzy method (returns Optional<T>) --> This IS a soft-delete */

        Optional<Task> foundTask = taskRepository.findById(id);

        if(foundTask.isPresent()){
            foundTask.get().setIsDeleted(true);
            taskRepository.save(foundTask.get());
        }


    }

    @Override
    public int getUnfinishedTaskCount(String projectCode) {

        return taskRepository.totalNonCompletedTasks(projectCode);

    }

    @Override
    public int getFinishedTaskCount(String projectCode) {
        return taskRepository.totalCompletedTasks(projectCode);

    }

    @Override
    public void deleteByProject(ProjectDTO dto) {

        Project project = projectMapper.convertToEntity(dto);

        List<Task> taskList = taskRepository.findAllByProject(project);

        taskList.forEach(task -> delete(task.getId()));

    }

    @Override
    public void completeByProject(ProjectDTO dto) {

        Project project = projectMapper.convertToEntity(dto);

        List<Task> taskList = taskRepository.findAllByProject(project);

        taskList.forEach(task -> task.setTaskStatus(Status.COMPLETE));

    }

    @Override
    public List<TaskDTO> listAllIncompleteTasks(Status status) {

        return  taskRepository.findAllByTaskStatus(status).stream()
                .map(taskMapper::convertToDto)
                .collect(Collectors.toList());

        // ^^^ Is returning a List of All Tasks(by status) :: Not showing Tasks (specific to an employee)

    }

    @Override
    public List<TaskDTO> listAllCompleteTasks(Status status) {


        return taskRepository.findAllByTaskStatusIsNot(status).stream()
                .map(taskMapper::convertToDto)
                .collect(Collectors.toList());
    }




}
