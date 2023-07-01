package pl.edu.agh.productivitypal.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.productivitypal.dto.DoneAndUndoneTaskDto;
import pl.edu.agh.productivitypal.model.AppUser;
import pl.edu.agh.productivitypal.model.Calendar;
import pl.edu.agh.productivitypal.model.CalendarTask;
import pl.edu.agh.productivitypal.model.Task;
import pl.edu.agh.productivitypal.repository.AppUserRepository;
import pl.edu.agh.productivitypal.repository.CalendarTaskRepository;
import pl.edu.agh.productivitypal.repository.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {

    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;
    private final CalendarTaskRepository calendarTaskRepository;

    public StatisticService(TaskRepository taskRepository, AppUserRepository appUserRepository, CalendarTaskRepository calendarTaskRepository) {
        this.taskRepository = taskRepository;
        this.appUserRepository = appUserRepository;
        this.calendarTaskRepository = calendarTaskRepository;
    }

    public DoneAndUndoneTaskDto getDoneAndUndoneTask(Long id, LocalDate startDate) {

        List<CalendarTask> calendarTasks = calendarTaskRepository.findAllByUserIdAndGivenPeriodOfTime(id, startDate);
//        List<Task> doneTasks =  calendarTasks.stream().map(CalendarTask::getTask).filter(Task::isCompleted).toList();
        List<Task> allTasks =  calendarTasks.stream().map(CalendarTask::getTask).toList();
        List<Task> doneTasks = new ArrayList<>();
        List<Task> undoneTasks = new ArrayList<>();

        for (Task task : allTasks){
            if (task.isCompleted()){
                doneTasks.add(task);
            } else {
                undoneTasks.add(task);
            }
        }

        Long totalTimeEstimated = 0L;
        Long totalCompletedTime = 0L;

        for (Task task : allTasks){
            totalTimeEstimated += task.getTimeEstimate() == null ? 0 : task.getTimeEstimate();
            totalCompletedTime += task.getCompletionTime() == null ? 0 : task.getCompletionTime();
        }

        return DoneAndUndoneTaskDto.builder()
                .done(doneTasks.size())
                .undone(undoneTasks.size())
                .doneTasks(doneTasks)
                .undoneTasks(undoneTasks)
                .totalCompletedTime(totalCompletedTime)
                .totalTimeEstimated(totalTimeEstimated)
                .build();
    }

}
