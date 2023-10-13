package pl.edu.agh.productivitypal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "calendar_task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarTask{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private Calendar calendar;

    @OneToOne
    private Task task;
}
