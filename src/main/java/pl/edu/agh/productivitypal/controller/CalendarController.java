package pl.edu.agh.productivitypal.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.productivitypal.model.Calendar;
import pl.edu.agh.productivitypal.model.CalendarTask;
import pl.edu.agh.productivitypal.service.CalendarService;

import java.util.List;

@RestController
@RequestMapping(value = "/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/all")
    public List<Calendar> getCalendars() {
        return calendarService.getAllCalendars();
    }

    @GetMapping
    public List<Calendar> getCalendarsOfCurrentUser(@RequestParam Long id) {
        return calendarService.getAllCalendarsOfCurrentUser(id);
    }

    @GetMapping("/{id}")
    public List<CalendarTask> getCalendarTasks(@PathVariable Long id) {
        return calendarService.getCalendarTasks(id);
    }

    @GetMapping("/{calendarId}/task/{taskId}")
    public CalendarTask getCalendarTask(@PathVariable Long calendarId, @PathVariable Long taskId) {
        return calendarService.getCalendarTask(calendarId, taskId);
    }

    @PostMapping
    public void addCalendar(@RequestBody Calendar calendar) {
        calendarService.addCalendar(calendar);
    }

    @PostMapping("/task/{id}")
    public void addTaskToCalendar(@RequestBody CalendarTask calendarTask, @PathVariable Long id) {
        calendarService.addTaskToCalendar(calendarTask, id);
    }

    @PutMapping("/{id}")
    public void updateCalendar(@RequestBody Calendar calendar, @PathVariable Long id) {
        calendarService.updateCalendar(calendar, id);
    }

    @PutMapping("{calendarId}/task/{taskId}")
    public void updateCalendarTask(@RequestBody CalendarTask calendarTask, @PathVariable Long calendarId, @PathVariable Long taskId) {
        calendarService.updateCalendarTask(calendarTask, calendarId, taskId);
    }

    @DeleteMapping("/{id}")
    public void deleteCalendar(@PathVariable Long id) {
        calendarService.deleteCalendar(id);
    }

    @DeleteMapping("/{calendarId}/task/{taskId}")
    public void deleteCalendarTask(@PathVariable Long calendarId, @PathVariable Long taskId) {
        calendarService.deleteCalendarTask(calendarId, taskId);
    }
}
