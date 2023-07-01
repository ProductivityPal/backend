package pl.edu.agh.productivitypal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/doneAndUndoneTasks")
    public DoneAndUndoneTaskDto getDoneAndUndoneTask(@RequestParam Long id,
                                                     @RequestParam LocalDate startDate){
        return statisticService.getDoneAndUndoneTask(id, startDate);
    }

}
