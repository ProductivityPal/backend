package pl.edu.agh.productivitypal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.agh.productivitypal.model.AppUser;
import pl.edu.agh.productivitypal.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByParentId(Integer id);

    List<Task> findAllByParentIdAndAndAppUserId(Integer parentId, Integer id);

    Task findByIdAndParentId(Integer id, Integer parentId);

    @Query("select task from Task task where task.deadline >= :startDate and task.deadline <= :endDate and task.appUser.id = :id")
    List<Task> findAllByAppUserIdAndDeadlineBetween( @Param("id") Integer id,  @Param("startDate") LocalDateTime startDate,  @Param("endDate") LocalDateTime endDate);

    List<Task> findAllByAppUserId(Integer id);

    @Query(value = "SELECT * FROM task", nativeQuery = true)
    List<Task> findAllQuery();


}
