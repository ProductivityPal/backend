package pl.edu.agh.productivitypal.model;

import jakarta.persistence.*;
import lombok.Getter;
import pl.edu.agh.productivitypal.enums.EnergyLevel;

import java.util.List;

@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private EnergyLevel energyLevel;

    @OneToMany(mappedBy = "appUser")
    private List<Task> task;

    public EnergyLevel getEnergyLevel() {
        return energyLevel;
    }
}
