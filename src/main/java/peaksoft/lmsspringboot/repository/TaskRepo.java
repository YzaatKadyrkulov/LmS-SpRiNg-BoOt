package peaksoft.lmsspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.lmsspringboot.entity.Group;
import peaksoft.lmsspringboot.entity.Task;

import java.util.List;
@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {
    @Query("select t from Task  t where t.lesson.id=:id")
    List<Task> getAllTasks(@Param("id") Long lessonId);

}
