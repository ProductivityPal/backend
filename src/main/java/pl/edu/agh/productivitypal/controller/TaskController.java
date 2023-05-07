package pl.edu.agh.productivitypal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @GetMapping("/")
    public String hello() {
        return "Hello Productivity!";
    }

}
