package pl.edu.agh.productivitypal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import pl.edu.agh.productivitypal.model.enums.EnergyLevel;

@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
//    private EnergyLevel energyLevel;
    private int energyLevel;

}
