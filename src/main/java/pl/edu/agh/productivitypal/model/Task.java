package pl.edu.agh.productivitypal.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "task")
public class Task {
    @Id
    private Long id;
    private Long userId;
    private Long categoryId;
    private String name;
    private int priority;
    private int difficulty;
    private LocalDate deadline;
    private Long timeEstimate;
    private Long completionTime;
    private boolean isSubtask;
    private boolean isParent;

    @ManyToOne
    private AppUser appUser;
}


