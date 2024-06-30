package peaksoft.lmsspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.lmsspringboot.entity.Course;

import java.util.List;
@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {
    @Query("SELECT c FROM Course c WHERE c.company.id = :comId")
    List<Course> getAllCourses(@Param("comId") Long comId);
    @Query("SELECT c FROM Course c join c.groups g  where g.id = :groupId")
    List<Course> getAllCourseByGroupId(@Param("groupId") Long groupId);

}
