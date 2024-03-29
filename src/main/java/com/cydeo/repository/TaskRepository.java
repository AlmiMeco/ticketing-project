package com.cydeo.repository;

import com.cydeo.entity.Project;
import com.cydeo.entity.Task;
import com.cydeo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    void deleteById(Long id);

    List<Task> findAllByProject(Project project);

    List<Task> findAllByTaskStatus(Status status);

    List<Task> findAllByTaskStatusIsNot(Status status);

//    Task getTaskById(Long id); <- I tried :p { doesn't work because [Task] <- does not have an 'ID' field }

    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.projectCode = ?1 AND t.taskStatus <> 'COMPLETE'")
    int totalNonCompletedTasks(String prjectCode);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.projectCode = ?1 AND t.taskStatus = 'COMPLETE'")
    int totalCompletedTasks(String projectCode);
}
