package pl.edu.agh.productivitypal.model;

import jakarta.persistence.*;
import pl.edu.agh.productivitypal.model.enums.Difficulty;
import pl.edu.agh.productivitypal.model.enums.Likeliness;

import java.time.LocalDate;

@Entity
@Table(name = "subtask")
public class Subtask {
    @Id
    private Long id;
    private String name;
    private String description;
    private int priority;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @Enumerated(EnumType.STRING)
    private Likeliness likeliness;
    private LocalDate deadline;
    private Long timeEstimate;
    private boolean is_completed;


    @ManyToOne
    private Task task;

}
