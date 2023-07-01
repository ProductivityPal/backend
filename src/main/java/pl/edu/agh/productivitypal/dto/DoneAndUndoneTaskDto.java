package pl.edu.agh.productivitypal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import pl.edu.agh.productivitypal.model.Task;

import java.util.List;

@Builder
@Getter
public class DoneAndUndoneTaskDto {
    private final long done;
    private final long undone;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private final List<Task> doneTasks;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private final List<Task> undoneTasks;
    private final Long totalTimeEstimated;
    private final Long totalCompletedTime;
}
