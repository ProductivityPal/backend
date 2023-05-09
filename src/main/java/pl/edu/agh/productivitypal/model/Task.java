package pl.edu.agh.productivitypal.model;

import jakarta.persistence.*;
import pl.edu.agh.productivitypal.model.enums.Difficulty;
import pl.edu.agh.productivitypal.model.enums.Likeliness;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "task")
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

    @ManyToOne
    private AppUser appUser;

    @OneToMany(mappedBy = "task")
    private List<Subtask> subtask;

    @OneToOne
    private Category category;
}


