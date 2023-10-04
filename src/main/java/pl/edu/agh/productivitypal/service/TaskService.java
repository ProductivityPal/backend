package pl.edu.agh.productivitypal.service;

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

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;

    public TaskService(TaskRepository taskRepository, AppUserRepository appUserRepository, AppUserService appUserService) {
        this.taskRepository = taskRepository;
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getAllTasksOfCurrentUser(Jwt jwt, String order, String sortBy, int offset, int pageSize) {
        AppUser currentUser = appUserService.getUserByEmail(jwt);
        return taskRepository
                .findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.fromString(order.toUpperCase()), sortBy)))
                .stream()
                .filter(task -> task.getAppUser().getId().equals(currentUser.getId()))
                .collect(Collectors.toList());
    }


    public List<Task> getTasksSortedByAlgosort(Jwt jwt) {
        AppUser currentUser = appUserService.getUserByEmail(jwt);

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

        AppUser appUser = appUserRepository.findById(1).orElseThrow();


        List<Task> sortedTasks = tasks
            .stream()
            .filter(task -> {
                if (appUser.getEnergyLevel().equals(EnergyLevel.LOW)) {
                    return task.getDifficulty().getValue() <= 3 || task.getLikeliness().getValue() >= 4;
                } else if (appUser.getEnergyLevel().equals(EnergyLevel.MEDIUM)) {
                    return task.getDifficulty().getValue() <= 4 || task.getLikeliness().getValue() >= 3;
                } else {
                    return true;
                }
            })
            .collect(Collectors.toList());

        return sortedTasks;

    }

    public Integer addTask(Task task) {
        taskRepository.save(task);
        return task.getId();
    }

    public Task getTaskById(Integer id) {
        return taskRepository.findById(id).orElseThrow();
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
        Task taskToUpdate = taskRepository.findById(id).orElseThrow();
        taskToUpdate = updateTaskData(task, taskToUpdate);
        taskRepository.save(taskToUpdate);
        return taskToUpdate;
    }

    public Integer addSubtask(Integer id, Task subtask) {
        Task parentTask = taskRepository.findById(id).orElseThrow();
        parentTask.setParent(true);
        subtask.setSubtask(true);
        subtask.setParentId(id);
        taskRepository.save(subtask);
        return subtask.getId();
    }

    public void deleteTask(Integer id) {
        Task taskToDelete = taskRepository.findById(id).orElseThrow();
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
        Task taskToDelete = taskRepository.findById(id).orElseThrow();
        if (taskToDelete.isParent()) {
            List<Task> subtasks = taskRepository.findAllByParentId(id);
            for (Task subtask : subtasks) {
                taskRepository.deleteById(subtask.getId());
            }
        }
        taskRepository.deleteById(id);
    }

    public void deleteSubtask(Integer taskId, Integer subtaskId) {
        Task taskToDelete = taskRepository.findById(taskId).orElseThrow();
        List<Task> subtasks = taskRepository.findAllByParentId(taskId);
        if (subtasks.size() == 1) {
            taskToDelete.setParent(false);
        }
        taskRepository.deleteById(subtaskId);
    }

    public List<Task> getSubtasks(Jwt jwt, Integer parentId){
        AppUser currentUser = appUserService.getUserByEmail(jwt);
        return taskRepository.findAllByParentIdAndAndAppUserId(parentId, currentUser.getId());
    }

    public Task getSubtask(Integer taskId, Integer subtaskId){
        return taskRepository.findByIdAndParentId(subtaskId, taskId);
    }

    public Task updateSubtask(Integer taskId, Integer subtaskId, Task task){
        Task subtaskToUpdate = taskRepository.findByIdAndParentId(subtaskId, taskId);
        subtaskToUpdate = updateTaskData(task, subtaskToUpdate);
        taskRepository.save(subtaskToUpdate);
        return subtaskToUpdate;
    }

}
