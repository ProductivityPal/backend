package pl.edu.agh.productivitypal.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Getter
@Builder
public class StatisticRequest {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
}
