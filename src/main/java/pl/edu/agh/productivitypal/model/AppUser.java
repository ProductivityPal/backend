package pl.edu.agh.productivitypal.model;

import jakarta.persistence.*;
import pl.edu.agh.productivitypal.model.enums.EnergyLevel;

import java.util.List;

@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private EnergyLevel energyLevel;

    @OneToMany(mappedBy = "appUser")
    private List<Task> task;

}
