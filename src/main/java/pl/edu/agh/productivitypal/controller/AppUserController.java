package pl.edu.agh.productivitypal.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.productivitypal.enums.EnergyLevel;
import pl.edu.agh.productivitypal.model.AppUser;
import pl.edu.agh.productivitypal.service.AppUserService;

@RestController
@RequestMapping(value = "/user")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/")
    public void addUser(@RequestBody AppUser appUser) {
        appUserService.addUser(appUser);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/energyLevel/{energyLevel}")
    public void updateEnergyLevel(@PathVariable EnergyLevel energyLevel, @RequestParam Long id) {
        appUserService.updateEnergyLevel(id, energyLevel);
    }

}
