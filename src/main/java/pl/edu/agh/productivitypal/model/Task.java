package pl.edu.agh.productivitypal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    private Long id;
    private String name;
}
