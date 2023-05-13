package pl.edu.agh.productivitypal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.agh.productivitypal.enums.Difficulty;
import pl.edu.agh.productivitypal.enums.Likeliness;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Long parentId;

    @Transient
    private double priorityScore;

    @ManyToOne
    private AppUser appUser;


    @OneToOne
    private Category category;

    public Task(Long id, String name, String description, int priority, Difficulty difficulty, Likeliness likeliness, LocalDate deadline, Long timeEstimate, Long completionTime, boolean isSubtask, boolean isParent, boolean isCompleted, Long parentId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.difficulty = difficulty;
        this.likeliness = likeliness;
        this.deadline = deadline;
        this.timeEstimate = timeEstimate;
        this.completionTime = completionTime;
        this.isSubtask = isSubtask;
        this.isParent = isParent;
        this.isCompleted = isCompleted;
        this.parentId = parentId;

    }


}


