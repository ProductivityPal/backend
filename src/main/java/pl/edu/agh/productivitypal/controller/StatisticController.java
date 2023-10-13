package pl.edu.agh.productivitypal.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.productivitypal.dto.DoneAndUndoneTaskDto;
import pl.edu.agh.productivitypal.service.StatisticService;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/statistic")
public class StatisticController {
    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/doneAndUndoneTasks")
    public DoneAndUndoneTaskDto getDoneAndUndoneTask(@RequestParam Integer id,
                                                     @RequestParam LocalDate startDate){
        return statisticService.getDoneAndUndoneTask(id, startDate);
    }

}
