package pl.edu.agh.productivitypal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.productivitypal.model.enums.EnergyLevel;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Productivity!";
    }

    @GetMapping("/get")
    public String enumTest(@RequestParam(required = false) EnergyLevel level) {
        return level.name();
    }

}
