package pl.edu.agh.productivitypal.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.productivitypal.enums.EnergyLevel;
import pl.edu.agh.productivitypal.model.AppUser;
import pl.edu.agh.productivitypal.repository.AppUserRepository;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public void addUser(AppUser appUser){
        appUserRepository.save(appUser);
    }

    public void updateEnergyLevel(Integer id, EnergyLevel energyLevel){
        AppUser appUser = appUserRepository.findById(id).orElseThrow();
        if (energyLevel != null){
            appUser.setEnergyLevel(energyLevel);
            appUserRepository.save(appUser);
        }
    }
}
