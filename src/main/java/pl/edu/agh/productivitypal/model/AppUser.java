package pl.edu.agh.productivitypal.model;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.agh.productivitypal.enums.EnergyLevel;

import java.util.List;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonValue
    private Long id;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private EnergyLevel energyLevel;

    @OneToMany(mappedBy = "appUser")
    private List<Task> task;

    @OneToMany(mappedBy = "appUser")
    private List<Calendar> calendar;

    public EnergyLevel getEnergyLevel() {
        return energyLevel;
    }
}
