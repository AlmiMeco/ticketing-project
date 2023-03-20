package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO, Long> implements TaskService {


    @Override
    public TaskDTO save(TaskDTO taskDTO) {

        if (taskDTO.getTaskStatus() == null)
            taskDTO.setTaskStatus(Status.OPEN);

        if (taskDTO.getAssignedDate() == null)
            taskDTO.setAssignedDate(LocalDate.now());

        if (taskDTO.getTaskID() == null)
            taskDTO.setTaskID(UUID.randomUUID().getMostSignificantBits());

        return super.save(taskDTO.getTaskID(), taskDTO);
    }

    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void update(TaskDTO taskDTO) {

        if (taskDTO.getTaskStatus() == null)
            taskDTO.setTaskStatus(Status.OPEN);

        if (taskDTO.getAssignedDate() == null)
            taskDTO.setAssignedDate(LocalDate.now());

        super.update(taskDTO.getTaskID(), taskDTO);
    }

    @Override
    public List<TaskDTO> findTasksByManager(UserDTO manager) {
        return findAll()
                        .stream()
                        .filter(taskDTO -> taskDTO.getProject().getAssignedManager().equals(manager))
                        .collect(Collectors.toList());
    }
}
