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


    public List<Task> getTasksSortedByAlgosort() {

        List<Task> tasks = taskRepository.findAll();

        LocalDate now = LocalDate.now();
        for (Task task : tasks){
            Period difference = Period.between(task.getDeadline(), now);
            double daysUntilDeadline = Math.max(0, difference.getDays());
            double priorityScore = Weight.DEADLINE.getValue() * (1 - daysUntilDeadline / 7) +
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

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    public Task updateTask(Long id, Task task){
        Task taskToUpdate = taskRepository.findById(id).orElseThrow();
        taskToUpdate.setName(task.getName());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setPriority(task.getPriority());
        taskToUpdate.setDifficulty(task.getDifficulty());
        taskToUpdate.setLikeliness(task.getLikeliness());
        taskToUpdate.setDeadline(task.getDeadline());
        taskToUpdate.setTimeEstimate(task.getTimeEstimate());
        taskToUpdate.setCompletionTime(task.getCompletionTime());
        taskToUpdate.setSubtask(task.isSubtask());
        taskToUpdate.setParent(task.isParent());
        taskToUpdate.setCompleted(task.isCompleted());
        taskToUpdate.setPriorityScore(task.getPriorityScore());
        taskToUpdate.setAppUser(task.getAppUser());
        taskToUpdate.setCategory(task.getCategory());
        taskRepository.save(taskToUpdate);
        return taskToUpdate;
    }

    public Task addSubtask(Long id, Task subtask) {
        Task parentTask = taskRepository.findById(id).orElseThrow();
        parentTask.setParent(true);
        subtask.setSubtask(true);
        subtask.setParentId(id);
        taskRepository.save(subtask);
        return parentTask;
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
}
