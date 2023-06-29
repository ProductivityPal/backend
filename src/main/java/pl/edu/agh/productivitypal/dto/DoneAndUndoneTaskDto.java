package pl.edu.agh.productivitypal.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DoneAndUndoneTaskDto {
    private final long done;
    private final long undone;
}
