package com.cydeo.repository;

import com.cydeo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    void deleteById(Long id);

//    Task getTaskById(Long id); <- I tried :p { doesn't work because [Task] <- does not have an 'ID' field }



}
