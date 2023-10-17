package pl.edu.agh.productivitypal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.productivitypal.dto.DoneAndUndoneTaskDto;
import pl.edu.agh.productivitypal.service.StatisticService;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/statistic")
public class StatisticController {
    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/doneAndUndoneTasks")
    public ResponseEntity<DoneAndUndoneTaskDto> getDoneAndUndoneTask(@RequestParam Integer id,
                                               @RequestParam LocalDateTime startDate){
        return ResponseEntity.ok(statisticService.getDoneAndUndoneTask(id, startDate));
    }

}
