package pl.edu.agh.productivitypal.model;

import jakarta.persistence.*;
import lombok.Getter;
import pl.edu.agh.productivitypal.enums.Difficulty;
import pl.edu.agh.productivitypal.enums.Likeliness;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "task")
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Long completionTime;
    private boolean isSubtask;
    private boolean isParent;
    private boolean isCompleted;

    @Transient
    private double priorityScore;

    @ManyToOne
    private AppUser appUser;


    @OneToOne
    private Category category;

    public void setPriorityScore(double priorityScore) {
        this.priorityScore = priorityScore;
    }
}


