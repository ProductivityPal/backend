package pl.edu.agh.productivitypal.service;

import org.springframework.stereotype.Service;
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

    public TaskService(TaskRepository taskRepository, AppUserRepository appUserRepository) {
        this.taskRepository = taskRepository;
        this.appUserRepository = appUserRepository;
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getAllTasksOfCurrentUser(Long id) {
        return taskRepository
                .findAll()
                .stream()
                .filter(task -> task.getAppUser().getId().equals(id))
                .collect(Collectors.toList());
    }


    public List<Task> getTasksSortedByAlgosort(Long id) {

        List<Task> tasks = taskRepository
                .findAll()
                .stream()
                .filter(task -> task.getAppUser().getId().equals(id))
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

        AppUser appUser = appUserRepository.findById(1L).orElseThrow();


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

    public Long addTask(Task task) {
        taskRepository.save(task);
        return task.getId();
    }

    public Task getTaskById(Long id) {
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

    public Task updateTask(Long id, Task task){
        Task taskToUpdate = taskRepository.findById(id).orElseThrow();
        taskToUpdate = updateTaskData(task, taskToUpdate);
        taskRepository.save(taskToUpdate);
        return taskToUpdate;
    }

    public Long addSubtask(Long id, Task subtask) {
        Task parentTask = taskRepository.findById(id).orElseThrow();
        parentTask.setParent(true);
        subtask.setSubtask(true);
        subtask.setParentId(id);
        taskRepository.save(subtask);
        return subtask.getId();
    }

    public void deleteTask(Long id) {
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

    public void deleteTaskAndAllSubtask(Long id) {
        Task taskToDelete = taskRepository.findById(id).orElseThrow();
        if (taskToDelete.isParent()) {
            List<Task> subtasks = taskRepository.findAllByParentId(id);
            for (Task subtask : subtasks) {
                taskRepository.deleteById(subtask.getId());
            }
        }
        taskRepository.deleteById(id);
    }

    public void deleteSubtask(Long taskId, Long subtaskId) {
        Task taskToDelete = taskRepository.findById(taskId).orElseThrow();
        List<Task> subtasks = taskRepository.findAllByParentId(taskId);
        if (subtasks.size() == 1) {
            taskToDelete.setParent(false);
        }
        taskRepository.deleteById(subtaskId);
    }

    public List<Task> getSubtasks(Long id, Long parentId){
        return taskRepository.findAllByParentIdAndAndAppUserId(parentId, id);
    }

    public Task getSubtask(Long taskId, Long subtaskId){
        return taskRepository.findByIdAndParentId(subtaskId, taskId);
    }

    public Task updateSubtask(Long taskId, Long subtaskId, Task task){
        Task subtaskToUpdate = taskRepository.findByIdAndParentId(subtaskId, taskId);
        subtaskToUpdate = updateTaskData(task, subtaskToUpdate);
        taskRepository.save(subtaskToUpdate);
        return subtaskToUpdate;
    }

}
