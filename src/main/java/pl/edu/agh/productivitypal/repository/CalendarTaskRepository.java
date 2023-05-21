package pl.edu.agh.productivitypal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.productivitypal.model.CalendarTask;

import java.util.List;

@Repository
public interface CalendarTaskRepository extends JpaRepository<CalendarTask, Long> {
    List<CalendarTask> findAllByCalendarId(Long id);

    CalendarTask findByCalendarIdAndTaskId(Long calendarId, Long taskId);
}
