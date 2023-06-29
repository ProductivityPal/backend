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

    public DoneAndUndoneTaskDto getNumberOfDoneAndUndoneTask(Long id, LocalDate startDate) {
        long doneTasks = 0;
        long undoneTasks = 0;

        List<CalendarTask> calendarTasks = calendarTaskRepository.findAllByUserIdAndGivenPeriodOfTime(id, startDate);

        List<Task> userTasks = calendarTasks.stream().map(CalendarTask::getTask).toList();
        doneTasks = userTasks.stream().filter(Task::isCompleted).count();
        undoneTasks = userTasks.size() - doneTasks;

        return DoneAndUndoneTaskDto.builder()
                .done(doneTasks)
                .undone(undoneTasks)
                .build();
    }
}
