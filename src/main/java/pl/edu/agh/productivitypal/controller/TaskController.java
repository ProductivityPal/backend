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


    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/")
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/algosort")
    public List<Task> getTasksSortedByAlgosort() {
        return taskService.getTasksSortedByAlgosort();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addTask(@RequestBody Task task) {
        taskService.addTask(task);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/{id}/subtask")
    public Task addSubtask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.addSubtask(id, task);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/{id}/subtask")
    public void deleteTaskAndAllSubtask(@PathVariable Long id) {
        taskService.deleteTaskAndAllSubtask(id);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/{taskId}/subtask/{subtaskId}")
    public void deleteTask(@PathVariable Long taskId, @PathVariable Long subtaskId){
        taskService.deleteSubtask(taskId, subtaskId);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{id}/subtask")
    public List<Task> getSubtasks(@PathVariable Long id) {
        return taskService.getSubtasks(id);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{taskId}/subtask/{subtaskId}")
    public Task getSubtask(@PathVariable Long taskId, @PathVariable Long subtaskId){
        return taskService.getSubtask(taskId, subtaskId);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/{taskId}/subtask/{subtaskId}")
    public Task updateSubtask(@PathVariable Long taskId, @PathVariable Long subtaskId, @RequestBody Task task){
        return taskService.updateSubtask(taskId, subtaskId, task);
    }
}
