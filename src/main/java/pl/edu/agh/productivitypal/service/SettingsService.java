package pl.edu.agh.productivitypal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.productivitypal.config.Jwt;
import pl.edu.agh.productivitypal.dto.SettingsLoginDto;
import pl.edu.agh.productivitypal.model.AppUser;
import pl.edu.agh.productivitypal.repository.AppUserRepository;

@Slf4j
@Service
public class SettingsService {

    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;
    private final CalendarService calendarService;
    private final TaskService taskService;
    private final EnergyLevelInfoService energyLevelInfoService;


    public SettingsService(AppUserService appUserService, PasswordEncoder passwordEncoder, AppUserRepository appUserRepository, CalendarService calendarService, TaskService taskService, EnergyLevelInfoService energyLevelInfoService) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
        this.calendarService = calendarService;
        this.taskService = taskService;
        this.energyLevelInfoService = energyLevelInfoService;
    }

    public void changeLoginData(Jwt jwt, SettingsLoginDto settingsLoginDto) {
        AppUser currentUser = appUserService.getUserByEmail(jwt);
        log.info("Current user: id {} name {}", currentUser.getId(), currentUser.getUsername());

        if (settingsLoginDto.getEmail() == null && settingsLoginDto.getPassword() == null) {
            log.info("No data to change.");
            return;
        }
        if (settingsLoginDto.getEmail() != null) {
            currentUser.setEmail(settingsLoginDto.getEmail());
        }
        if (settingsLoginDto.getPassword() != null) {
            currentUser.setPassword(passwordEncoder.encode(settingsLoginDto.getPassword()));
        }
        appUserRepository.save(currentUser);
    }

    @Transactional
    public void deleteAccount(Jwt jwt) {
        AppUser currentUser = appUserService.getUserByEmail(jwt);
        log.info("Current user: id {} name {}", currentUser.getId(), currentUser.getUsername());

        log.info("Deleting user: id {} name {}", currentUser.getId(), currentUser.getUsername());

        calendarService.deleteCalendar(jwt, currentUser.getCalendar().get(0));
        taskService.deleteAllTasksOfCurrentUser(currentUser);

        appUserRepository.delete(currentUser);
    }
}
