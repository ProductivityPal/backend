package pl.edu.agh.productivitypal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.agh.productivitypal.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByParentId(Long id);

    List<Task> findAllByParentIdAndAndAppUserId(Long parentId, Long id);

    Task findByIdAndParentId(Long id, Long parentId);

    @Query(value = "SELECT * FROM task", nativeQuery = true)
    List<Task> findAllQuery();
}
