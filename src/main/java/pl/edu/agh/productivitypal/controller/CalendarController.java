package pl.edu.agh.productivitypal.controller;

import org.springframework.http.ResponseEntity;
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

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/all")
    public ResponseEntity<List<Calendar>> getCalendars() {
        return ResponseEntity.ok(calendarService.getAllCalendars());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<Calendar>> getCalendarsOfCurrentUser(@RequestHeader(AUTHORIZATION_HEADER) Jwt jwt) {
        return ResponseEntity.ok(calendarService.getAllCalendarsOfCurrentUser(jwt));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<List<CalendarTask>> getCalendarTasks(@PathVariable Integer id) {
        return ResponseEntity.ok(calendarService.getCalendarTasks(id));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{calendarId}/task/{taskId}")
    public ResponseEntity<CalendarTask> getCalendarTask(@PathVariable Integer calendarId, @PathVariable Integer taskId) {
        return ResponseEntity.ok(calendarService.getCalendarTask(calendarId, taskId));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<String> addCalendar(@RequestHeader(AUTHORIZATION_HEADER) Jwt jwt, @RequestBody Calendar calendar) {
        calendarService.addCalendar(jwt, calendar);
        return ResponseEntity.ok("Calendar " + calendar.getName() + " was added");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/task/{id}")
    public ResponseEntity<String> addTaskToCalendar(@RequestHeader(AUTHORIZATION_HEADER) Jwt jwt, @RequestBody CalendarTask calendarTask, @PathVariable Integer id) {
        calendarService.addTaskToCalendar(jwt, calendarTask, id);
        return ResponseEntity.ok("Task " + calendarTask.getTask().getName() + " was added to calendar " +  calendarTask.getCalendar().getName());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCalendar(@RequestBody Calendar calendar, @PathVariable Integer id) {
        calendarService.updateCalendar(calendar, id);
        return ResponseEntity.ok("Calendar " + calendar.getName() + " information was updated");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("{calendarId}/task/{taskId}")
    public ResponseEntity<String> updateCalendarTask(@RequestBody CalendarTask calendarTask, @PathVariable Integer calendarId, @PathVariable Integer taskId) {
        calendarService.updateCalendarTask(calendarTask, calendarId, taskId);
        return ResponseEntity.ok("Calendar task " + calendarTask.getTask().getName() + " information was updated");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCalendar(@PathVariable Integer id) {
        calendarService.deleteCalendar(id);
        return ResponseEntity.ok("Calendar was deleted successfully");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{calendarId}/task/{taskId}")
    public ResponseEntity<String> deleteCalendarTask(@PathVariable Integer calendarId, @PathVariable Integer taskId) {
        calendarService.deleteCalendarTask(calendarId, taskId);
        return ResponseEntity.ok("Task from calendar was deleted successfully");
    }
}
