package pl.edu.agh.productivitypal.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.productivitypal.config.Jwt;
import pl.edu.agh.productivitypal.model.Calendar;
import pl.edu.agh.productivitypal.model.CalendarTask;
import pl.edu.agh.productivitypal.service.CalendarService;

import java.util.List;

import static pl.edu.agh.productivitypal.config.SecurityConstant.AUTHORIZATION_HEADER;

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
    public List<Calendar> getCalendarsOfCurrentUser(@RequestHeader(AUTHORIZATION_HEADER) Jwt jwt) {
        return calendarService.getAllCalendarsOfCurrentUser(jwt);
    }

    @GetMapping("/{id}")
    public List<CalendarTask> getCalendarTasks(@PathVariable Integer id) {
        return calendarService.getCalendarTasks(id);
    }

    @GetMapping("/{calendarId}/task/{taskId}")
    public CalendarTask getCalendarTask(@PathVariable Integer calendarId, @PathVariable Integer taskId) {
        return calendarService.getCalendarTask(calendarId, taskId);
    }

    @PostMapping
    public void addCalendar(@RequestBody Calendar calendar) {
        calendarService.addCalendar(calendar);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/task/{id}")
    public void addTaskToCalendar(@RequestBody CalendarTask calendarTask, @PathVariable Integer id) {
        calendarService.addTaskToCalendar(calendarTask, id);
    }

    @PutMapping("/{id}")
    public void updateCalendar(@RequestBody Calendar calendar, @PathVariable Integer id) {
        calendarService.updateCalendar(calendar, id);
    }

    @PutMapping("{calendarId}/task/{taskId}")
    public void updateCalendarTask(@RequestBody CalendarTask calendarTask, @PathVariable Integer calendarId, @PathVariable Integer taskId) {
        calendarService.updateCalendarTask(calendarTask, calendarId, taskId);
    }

    @DeleteMapping("/{id}")
    public void deleteCalendar(@PathVariable Integer id) {
        calendarService.deleteCalendar(id);
    }

    @DeleteMapping("/{calendarId}/task/{taskId}")
    public void deleteCalendarTask(@PathVariable Integer calendarId, @PathVariable Integer taskId) {
        calendarService.deleteCalendarTask(calendarId, taskId);
    }
}