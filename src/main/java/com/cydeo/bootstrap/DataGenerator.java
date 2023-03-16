package com.cydeo.bootstrap;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Gender;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.RoleService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataGenerator implements CommandLineRunner {

    private final RoleService roleService;   // Dependency Injection
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    public DataGenerator(RoleService roleService, UserService userService, ProjectService projectService, TaskService taskService) {
        this.roleService = roleService;
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }


    @Override
    public void run(String... args) throws Exception {

        // anything we put inside this method will load/run amongst app execution (first thing to execute)


        // create roles and add to the dataBase

        RoleDTO adminRole = new RoleDTO(1L, "Admin");
        RoleDTO managerRole = new RoleDTO(2L, "Manager");
        RoleDTO employeeRole = new RoleDTO(3L, "Employee");


        roleService.save(adminRole);
        roleService.save(managerRole);
        roleService.save(employeeRole);

        UserDTO user1 = new UserDTO("John", "Kesy",
                "john@cydeo.com", "Abc1", "7459684532",true , adminRole, Gender.MALE);

        UserDTO user5 = new UserDTO("Mike", "Smith",
                "mike@cydeo.com", "Abc2", "7459684532", true, adminRole, Gender.MALE);

        UserDTO user2 = new UserDTO("Delisa",
                "Norre", "delisa@cydeo.com", "123", "8567412358", true, managerRole, Gender.FEMALE);

        UserDTO user3 = new UserDTO("Craig", "Jark",
                "craig@cydeo.com", "Abc3", "7777775566", true, employeeRole, Gender.MALE);

        UserDTO user4 = new UserDTO("Shaun",
                "Hayns", "shaun@cydeo.com", "Abc4", "3256987412", true, managerRole, Gender.MALE);

        UserDTO user6 = new UserDTO("Elizebeth",
                "Loren", "elizebeth@cydeo.com", "Abc4", "5306987412", true, employeeRole, Gender.FEMALE);

        UserDTO user7 = new UserDTO("Maria",
                "Ada", "maria@cydeo.com", "Abc4", "9996987412", true, employeeRole, Gender.FEMALE);

        UserDTO user8 = new UserDTO("Bill",
                "Matt", "bill@cydeo.com", "Abc4", "8881239846", true, employeeRole, Gender.MALE);


        /* SAVING THESE USERS TO DATABASE (Map in this case) */
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
        userService.save(user6);
        userService.save(user7);
        userService.save(user8);


        ProjectDTO project1 = new ProjectDTO("Spring MVC", "PR001", user2, LocalDate.now(), LocalDate.now().plusDays(25), "Creating Controllers", Status.OPEN);
        ProjectDTO project2 = new ProjectDTO("Spring ORM", "PR002", user2, LocalDate.now(), LocalDate.now().plusDays(10), "Creating Database", Status.IN_PROGRESS);
        ProjectDTO project3 = new ProjectDTO("Spring Container", "PR003", user4, LocalDate.now(), LocalDate.now().plusDays(32), "Creating Container", Status.IN_PROGRESS);


        /* SAVING THESE PROJECTS TO DATABASE (Map in this case) */
        projectService.save(project1);
        projectService.save(project2);
        projectService.save(project3);




        TaskDTO task1 = new TaskDTO(1L,project1, user8, "Controller", "Request Mapping", Status.IN_PROGRESS, LocalDate.now().minusDays(4));
        TaskDTO task2 = new TaskDTO(2L,project3, user3, "Configuration", "Database Connection", Status.COMPLETED, LocalDate.now().minusDays(12));
        TaskDTO task3 = new TaskDTO(3L,project3, user6, "Mapping", "One-To-Many", Status.COMPLETED, LocalDate.now().minusDays(8));
        TaskDTO task4 = new TaskDTO(4L,project2, user7, "Dependency Injection", "Autowired", Status.IN_PROGRESS, LocalDate.now().minusDays(20));

        /* SAVING THESE TASKS TO DATABASE (Map in this case) */
        taskService.save(task1);
        taskService.save(task2);
        taskService.save(task3);
        taskService.save(task4);



    }


}
