package pl.edu.agh.productivitypal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.productivitypal.config.Jwt;
import pl.edu.agh.productivitypal.dto.ReportInfoDto;
import pl.edu.agh.productivitypal.dto.StatisticRequest;
import pl.edu.agh.productivitypal.model.EnergyLevelInfo;
import pl.edu.agh.productivitypal.service.StatisticService;

import java.time.LocalDateTime;
import java.util.List;

import static pl.edu.agh.productivitypal.config.SecurityConstant.AUTHORIZATION_HEADER;

@RestController
@RequestMapping(value = "/statistic")
public class StatisticController {
    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<ReportInfoDto> getInfoReport(@RequestHeader(AUTHORIZATION_HEADER) Jwt jwt,
                                                              @RequestBody StatisticRequest statisticRequest){
        return ResponseEntity.ok(statisticService.getInfoReport(jwt, statisticRequest.getStartDate(), statisticRequest.getEndDate()));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/energyLevel")
    public ResponseEntity<List<EnergyLevelInfo>> getEnergyLevelReport(@RequestHeader(AUTHORIZATION_HEADER) Jwt jwt){
        return ResponseEntity.ok(statisticService.getEnergyLevelReport(jwt));
    }

}
