package pl.edu.agh.productivitypal.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.productivitypal.model.Calendar;
import pl.edu.agh.productivitypal.model.CalendarTask;
import pl.edu.agh.productivitypal.model.Task;
import pl.edu.agh.productivitypal.repository.CalendarRepository;
import pl.edu.agh.productivitypal.repository.CalendarTaskRepository;
import pl.edu.agh.productivitypal.repository.TaskRepository;

import java.util.List;

@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final CalendarTaskRepository calendarTaskRepository;

    private final TaskRepository taskRepository;

    public CalendarService(CalendarRepository calendarRepository, CalendarTaskRepository calendarTaskRepository, TaskRepository taskRepository) {
        this.calendarRepository = calendarRepository;
        this.calendarTaskRepository = calendarTaskRepository;
        this.taskRepository = taskRepository;
    }

    public List<Calendar> getAllCalendars() {
        return calendarRepository.findAll();
    }

    public List<Calendar> getAllCalendarsOfCurrentUser(Long id) {
        return calendarRepository.findAllByAppUserId(id);
    }

    public List<CalendarTask> getCalendarTasks(Long id) {
        return calendarTaskRepository.findAllByCalendarId(id);
    }

    public void addCalendar(Calendar calendar) {
        calendarRepository.save(calendar);
    }

    public void addTaskToCalendar(CalendarTask calendarTask, Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        calendarTask.setTask(task);
        calendarTaskRepository.save(calendarTask);


    }

    public void updateCalendar(Calendar calendar, Long id) {
        Calendar calendarToUpdate = calendarRepository.findById(id).orElseThrow();
        if (calendar.getName() != null){
            calendarToUpdate.setName(calendar.getName());
        }
        calendarRepository.save(calendarToUpdate);
    }

    public void updateCalendarTask(CalendarTask calendarTask, Long calendarId, Long taskId) {
        CalendarTask calendarTaskToUpdate = calendarTaskRepository.findByCalendarIdAndTaskId(calendarId, taskId);
        if (calendarTask.getTask() != null){
            calendarTaskToUpdate.setTask(calendarTask.getTask());
        }
        if (calendarTask.getCalendar() != null){
            calendarTaskToUpdate.setCalendar(calendarTask.getCalendar());
        }
        if (calendarTask.getStartDate() != null){
            calendarTaskToUpdate.setStartDate(calendarTask.getStartDate());
        }
        if (calendarTask.getEndDate() != null){
            calendarTaskToUpdate.setEndDate(calendarTask.getEndDate());
        }
        calendarTaskRepository.save(calendarTaskToUpdate);
    }

    public void deleteCalendar(Long id) {
        List<CalendarTask> calendarTasks = calendarTaskRepository.findAllByCalendarId(id);
        calendarTaskRepository.deleteAll(calendarTasks);
        calendarRepository.deleteById(id);
    }

    public void deleteCalendarTask(Long calendarId, Long taskId) {
        CalendarTask calendarTask = calendarTaskRepository.findByCalendarIdAndTaskId(calendarId, taskId);
        calendarTaskRepository.delete(calendarTask);
    }

    public CalendarTask getCalendarTask(Long calendarId, Long taskId) {
        return calendarTaskRepository.findByCalendarIdAndTaskId(calendarId, taskId);
    }
}
