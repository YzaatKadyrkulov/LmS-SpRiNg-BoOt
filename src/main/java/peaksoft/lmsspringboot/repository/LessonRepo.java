package peaksoft.lmsspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.lmsspringboot.entity.Group;
import peaksoft.lmsspringboot.entity.Lesson;

import java.util.List;
@Repository
public interface LessonRepo extends JpaRepository<Lesson,Long> {
    @Query("select l from Lesson l where l.course.id = :courseId")
    List<Lesson> getAllLessonsByCourseId(@Param("courseId") Long courseId);
}
