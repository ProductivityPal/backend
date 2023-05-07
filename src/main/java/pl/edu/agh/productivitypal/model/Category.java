package pl.edu.agh.productivitypal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
    @Id
    private Long id;
    private Long userId;
    private String name;
}
