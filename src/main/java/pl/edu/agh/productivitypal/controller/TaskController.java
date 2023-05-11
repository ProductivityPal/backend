package pl.edu.agh.productivitypal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addTask(@RequestBody Task task) {
        taskService.addTask(task);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @PostMapping("/{id}/subtask")
    public Task addSubtask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.addSubtask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @DeleteMapping("/{id}/subtask")
    public void deleteTaskAndAllSubtask(@PathVariable Long id) {
        taskService.deleteTaskAndAllSubtask(id);
    }

    @DeleteMapping("/{taskId}/subtask/{subtaskId}")
    public void deleteTask(@PathVariable Long taskId, @PathVariable Long subtaskId){
        taskService.deleteSubtask(taskId, subtaskId);
    }
}
