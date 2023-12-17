package pl.edu.agh.productivitypal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.productivitypal.config.Jwt;
import pl.edu.agh.productivitypal.dto.CategoryInfoDto;
import pl.edu.agh.productivitypal.dto.ReportInfoDto;
import pl.edu.agh.productivitypal.model.*;
import pl.edu.agh.productivitypal.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StatisticService {

    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;
    private final CalendarTaskRepository calendarTaskRepository;
    private final AppUserService appUserService;
    private final EnergyLevelInfoRepository energyLevelInfoRepository;

    private final CategoryRepository categoryRepository;


    public StatisticService(TaskRepository taskRepository, AppUserRepository appUserRepository, CalendarTaskRepository calendarTaskRepository, AppUserService appUserService, EnergyLevelInfoRepository energyLevelInfoRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.appUserRepository = appUserRepository;
        this.calendarTaskRepository = calendarTaskRepository;
        this.appUserService = appUserService;
        this.energyLevelInfoRepository = energyLevelInfoRepository;
        this.categoryRepository = categoryRepository;
    }

    public ReportInfoDto getInfoReport(Jwt jwt, LocalDateTime startDate, LocalDateTime endDate) {
        AppUser currentUser = appUserService.getUserByEmail(jwt);
        log.info("Current user: id {} name {}", currentUser.getId(), currentUser.getUsername());

        List<Task> tasks = taskRepository.findAllByAppUserIdAndDeadlineBetween(currentUser.getId(), startDate, endDate);

        int done = 0;
        int undone = 0;

        List<Category> categoryNames = categoryRepository.findByAppUserId(currentUser.getId());
        List<CategoryInfoDto> categories = new ArrayList<>();

        for (int i = 0; i < 4; i++){
            CategoryInfoDto category = CategoryInfoDto.builder()
                    .name(categoryNames.get(i).getDefaultName())
                    .done(0)
                    .undone(0)
                    .averageEstimatedTime(0)
                    .averageCompletionTime(0)
                    .build();
            categories.add(category);
        }

        for (Task task : tasks){
            CategoryInfoDto category = categories.get(0);

            for (int i = 0; i < 4; i++){
                if (task.getCategory().equals(categoryNames.get(i).getDefaultName())){
                    category = categories.get(i);
                    break;
                }
            }

            if (task.isCompleted()){
                done++;
                category.setDone(category.getDone() + 1);
                category.setAverageCompletionTime(category.getAverageCompletionTime() + (task.getCompletionTime() == null ? 0 : task.getCompletionTime()));
            } else {
                undone++;
                category.setUndone(category.getUndone() + 1);
            }

            category.setAverageEstimatedTime(category.getAverageEstimatedTime() + (task.getTimeEstimate() == null ? 0 : task.getTimeEstimate()));
        }

        for (CategoryInfoDto categoryInfoDto : categories){
            if (categoryInfoDto.getDone() > 0){
                categoryInfoDto.setAverageCompletionTime(categoryInfoDto.getAverageCompletionTime() / (categoryInfoDto.getDone() ));
            }
            if (categoryInfoDto.getDone() + categoryInfoDto.getUndone() > 0){
                categoryInfoDto.setAverageEstimatedTime(categoryInfoDto.getAverageEstimatedTime() / (categoryInfoDto.getDone() + categoryInfoDto.getUndone()));
            }
        }

        return ReportInfoDto.builder()
                .done(done)
                .undone(undone)
                .categories(categories)
                .build();
    }

    public List<EnergyLevelInfo> getEnergyLevelReport(Jwt jwt){
        AppUser currentUser = appUserService.getUserByEmail(jwt);
        log.info("Current user: id {} name {}", currentUser.getId(), currentUser.getUsername());

        return energyLevelInfoRepository.findAllByAppUserIdAndNotificationTimeBetween(currentUser.getId(), LocalDateTime.now().plusDays(-7), LocalDateTime.now());
    }

}
