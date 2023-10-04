package pl.edu.agh.productivitypal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.productivitypal.config.Jwt;
import pl.edu.agh.productivitypal.model.Task;
import pl.edu.agh.productivitypal.enums.EnergyLevel;
import pl.edu.agh.productivitypal.service.TaskService;

import java.util.List;

import static pl.edu.agh.productivitypal.config.SecurityConstant.AUTHORIZATION_HEADER;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/all")
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<Task> getTasksOfCurrentUser(@RequestHeader(AUTHORIZATION_HEADER) Jwt jwt,
                                            @RequestParam(required = false, defaultValue = "asc") String order,
                                            @RequestParam(required = false, defaultValue = "id") String sortBy,
                                            @RequestParam(required = false, defaultValue = "0") int offset,
                                            @RequestParam(required = false, defaultValue = "50") int pageSize) {
        return taskService.getAllTasksOfCurrentUser(jwt, order, sortBy, offset, pageSize);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/algosort")
    public List<Task> getTasksSortedByAlgosort(@RequestHeader(AUTHORIZATION_HEADER) Jwt jwt) {
        return taskService.getTasksSortedByAlgosort(jwt);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public Integer addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Integer id) {
        return taskService.getTaskById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Integer id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/{id}/subtask")
    public Integer addSubtask(@PathVariable Integer id, @RequestBody Task task) {
        return taskService.addSubtask(id, task);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}/subtask")
    public void deleteTaskAndAllSubtask(@PathVariable Integer id) {
        taskService.deleteTaskAndAllSubtask(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{taskId}/subtask/{subtaskId}")
    public void deleteTask(@PathVariable Integer taskId, @PathVariable Integer subtaskId){
        taskService.deleteSubtask(taskId, subtaskId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}/subtask")
    public List<Task> getSubtasks(@PathVariable Integer id, @RequestHeader(AUTHORIZATION_HEADER) Jwt jwt) {
        return taskService.getSubtasks(jwt, id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{taskId}/subtask/{subtaskId}")
    public Task getSubtask(@PathVariable Integer taskId, @PathVariable Integer subtaskId){
        return taskService.getSubtask(taskId, subtaskId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{taskId}/subtask/{subtaskId}")
    public Task updateSubtask(@PathVariable Integer taskId, @PathVariable Integer subtaskId, @RequestBody Task task){
        return taskService.updateSubtask(taskId, subtaskId, task);
    }
}
