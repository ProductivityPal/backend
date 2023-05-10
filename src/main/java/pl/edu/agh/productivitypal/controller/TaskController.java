package pl.edu.agh.productivitypal.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.productivitypal.model.Task;
import pl.edu.agh.productivitypal.enums.EnergyLevel;
import pl.edu.agh.productivitypal.service.TaskService;

import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/")
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/algosort")
    public List<Task> getTasksSortedByAlgosort() {
        return taskService.getTasksSortedByAlgosort();
    }
}
