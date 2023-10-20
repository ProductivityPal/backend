package pl.edu.agh.productivitypal.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.edu.agh.productivitypal.config.Jwt;
import pl.edu.agh.productivitypal.enums.EnergyLevel;
import pl.edu.agh.productivitypal.enums.Weight;
import pl.edu.agh.productivitypal.model.AppUser;
import pl.edu.agh.productivitypal.model.Task;
import pl.edu.agh.productivitypal.repository.AppUserRepository;
import pl.edu.agh.productivitypal.repository.TaskRepository;
import pl.edu.agh.productivitypal.repository.dao.TaskSearchDao;
import pl.edu.agh.productivitypal.request.TaskRequest;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;
    private final TaskSearchDao taskSearchDao;

    public TaskService(TaskRepository taskRepository, AppUserRepository appUserRepository, AppUserService appUserService, TaskSearchDao taskSearchDao) {
        this.taskRepository = taskRepository;
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
        this.taskSearchDao = taskSearchDao;
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getAllTasksOfCurrentUser(Jwt jwt, String order, String sortBy, int offset, int pageSize) {
        AppUser currentUser = appUserService.getUserByEmail(jwt);
        log.info("Current user: id {} name {}", currentUser.getId(), currentUser.getUsername());
        return taskRepository
                .findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.fromString(order.toUpperCase()), sortBy)))
                .stream()
                .filter(task -> task.getAppUser().getId().equals(currentUser.getId()))
                .collect(Collectors.toList());
    }


    public List<Task> getTasksSortedByAlgosort(Jwt jwt) {
        AppUser currentUser = appUserService.getUserByEmail(jwt);
        log.info("Current user: id {} name {}", currentUser.getId(), currentUser.getUsername());

        List<Task> tasks = taskRepository
                .findAll()
                .stream()
                .filter(task -> task.getAppUser().getId().equals(currentUser.getId()))
                .collect(Collectors.toList());;

        LocalDate now = LocalDate.now();
        for (Task task : tasks){
            Period difference = Period.between(now, task.getDeadline());
            double daysUntilDeadline = Math.max(0, difference.getDays());
            double priorityScore = Weight.DEADLINE.getValue() * (1 / daysUntilDeadline) * 100 +
                    Weight.DIFFICULTY.getValue() * task.getDifficulty().getValue() +
                    Weight.TIME_ESTIMATE.getValue() * task.getTimeEstimate() +
                    Weight.LIKELINESS.getValue() * task.getLikeliness().getValue();
            task.setPriorityScore(priorityScore);
        }

        tasks.sort((a, b) -> Double.compare(b.getPriorityScore(), a.getPriorityScore()));

        List<Task> sortedTasks = tasks
            .stream()
            .filter(task -> {
                if (currentUser.getCurrentEnergyLevel().equals(EnergyLevel.LOW)) {
                    return task.getDifficulty().getValue() <= 3 || task.getLikeliness().getValue() >= 4;
                } else if (currentUser.getCurrentEnergyLevel().equals(EnergyLevel.MEDIUM)) {
                    return task.getDifficulty().getValue() <= 4 || task.getLikeliness().getValue() >= 3;
                } else {
                    return true;
                }
            })
            .collect(Collectors.toList());

        return sortedTasks;
    }

    public Integer addTask(Jwt jwt, Task task) {
        AppUser user = appUserService.getUserByEmail(jwt);
        log.info("Current user: id {} name {}", user.getId(), user.getUsername());
        task.setAppUser(user);
        taskRepository.save(task);
        return task.getId();
    }

    public Task getTaskById(Integer id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task with id: " + id +  "not found"));
    }

    private Task updateTaskData(Task task, Task taskToUpdate){
        if (task.getName() != null){
            taskToUpdate.setName(task.getName());
        }
        if(task.getDescription() != null){
            taskToUpdate.setDescription(task.getDescription());
        }
        if(task.getPriority() != taskToUpdate.getPriority()){
            taskToUpdate.setPriority(task.getPriority());
        }
        if(task.getDifficulty() != null){
            taskToUpdate.setDifficulty(task.getDifficulty());
        }
        if (task.getLikeliness() != null){
            taskToUpdate.setLikeliness(task.getLikeliness());
        }
        if (task.getDeadline() != null){
            taskToUpdate.setDeadline(task.getDeadline());
        }
        if (task.getTimeEstimate() != null){
            taskToUpdate.setTimeEstimate(task.getTimeEstimate());
        }
        if (task.getCompletionTime() != null){
            taskToUpdate.setCompletionTime(task.getCompletionTime());
        }
        if (task.getPriorityScore() != taskToUpdate.getPriorityScore()){
            taskToUpdate.setPriorityScore(task.getPriorityScore());
        }
        if (task.isParent() != taskToUpdate.isParent()){
            taskToUpdate.setParent(task.isParent());
        }
        if(task.isCompleted() != taskToUpdate.isCompleted()){
            taskToUpdate.setCompleted(task.isCompleted());
        }
        if (task.isSubtask() != taskToUpdate.isSubtask()){
            taskToUpdate.setSubtask(task.isSubtask());
        }
        if (task.getParentId() != taskToUpdate.getParentId()){
            taskToUpdate.setParentId(task.getParentId());
        }
        if (task.getAppUser() != null){
            taskToUpdate.setAppUser(task.getAppUser());
        }
        if (task.getCategory() != null){
            taskToUpdate.setCategory(task.getCategory());
        }

        return taskToUpdate;
    }

    public Task updateTask(Integer id, Task task){
        Task taskToUpdate = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task with id: " + id +  "not found"));
        log.info("Task to update: {} {}", taskToUpdate.getId(), taskToUpdate.getName());
        taskToUpdate = updateTaskData(task, taskToUpdate);
        taskRepository.save(taskToUpdate);
        return taskToUpdate;
    }

    public Integer addSubtask(Jwt jwt, Integer id, Task subtask) {
        AppUser user = appUserService.getUserByEmail(jwt);
        log.info("Current user: id {} name {}", user.getId(), user.getUsername());
        Task parentTask = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Parent task with id: " + id +  "not found"));
        log.info("Found parent task: {} {}", parentTask.getId(), parentTask.getName());
        parentTask.setParent(true);
        subtask.setSubtask(true);
        subtask.setParentId(id);
        subtask.setAppUser(user);
        taskRepository.save(subtask);
        return subtask.getId();
    }

    public void deleteTask(Integer id) {
        Task taskToDelete = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task with id: " + id +  "not found"));
        log.info("Task to delete: {} {}", taskToDelete.getId(), taskToDelete.getName());
        if (taskToDelete.isParent()) {
            List<Task> subtasks = taskRepository.findAllByParentId(id);
            for (Task subtask : subtasks) {
                subtask.setSubtask(false);
                subtask.setParentId(null);
            }
        }
        taskRepository.deleteById(id);
    }

    public void deleteTaskAndAllSubtask(Integer id) {
        Task taskToDelete = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task with id: " + id +  "not found"));
        log.info("Task to delete: {} {}", taskToDelete.getId(), taskToDelete.getName());
        if (taskToDelete.isParent()) {
            List<Task> subtasks = taskRepository.findAllByParentId(id);
            for (Task subtask : subtasks) {
                taskRepository.deleteById(subtask.getId());
            }
        }
        taskRepository.deleteById(id);
    }

    public void deleteSubtask(Integer taskId, Integer subtaskId) {
        Task taskToDelete = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task with id: " + taskId +  "not found"));
        List<Task> subtasks = taskRepository.findAllByParentId(taskId);
        if (subtasks.size() == 1) {
            taskToDelete.setParent(false);
        }
        taskRepository.deleteById(subtaskId);
    }

    public List<Task> getSubtasks(Jwt jwt, Integer parentId){
        AppUser currentUser = appUserService.getUserByEmail(jwt);
        log.info("Current user: {} {}", currentUser.getId(), currentUser.getUsername());
        return taskRepository.findAllByParentIdAndAndAppUserId(parentId, currentUser.getId());
    }

    public Task getSubtask(Integer taskId, Integer subtaskId){
        return taskRepository.findByIdAndParentId(subtaskId, taskId);
    }

    public Task updateSubtask(Integer taskId, Integer subtaskId, Task task){
        Task subtaskToUpdate = taskRepository.findByIdAndParentId(subtaskId, taskId);
        if (subtaskToUpdate == null){
            throw new EntityNotFoundException("Subtask with id: " + subtaskId +  "not found");
        }
        subtaskToUpdate = updateTaskData(task, subtaskToUpdate);
        taskRepository.save(subtaskToUpdate);
        return subtaskToUpdate;
    }

    public List<Task> getTasksByCriteria(TaskRequest taskRequest){
        return taskSearchDao.findAllByCriteria(taskRequest);
    }
}
