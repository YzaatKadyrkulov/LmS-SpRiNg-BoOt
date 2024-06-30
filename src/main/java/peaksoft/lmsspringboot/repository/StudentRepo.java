package peaksoft.lmsspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.lmsspringboot.entity.Group;
import peaksoft.lmsspringboot.entity.Student;

import java.util.List;
@Repository
public interface StudentRepo  extends JpaRepository<Student,Long> {
   @Query( "select s from Student s join s.group g join g.courses c join c.company co where co.id=:comId")
    List<Student> getAllByStudentsByCompanyId(@Param("comId") Long comId);
   @Query("select s from Student s join s.group g where g.id=:Id")
    List<Student> getAllByStudentsByGroupId(@Param("Id") Long groupId);
   @Query("SELECT s FROM Student s WHERE LOWER(s.formatStudy) LIKE '%online%' ORDER BY s.lastName")
    List<Student> getAllOnlineStudents();
    @Query("SELECT s FROM Student s WHERE LOWER(s.formatStudy) LIKE '%offline%' ORDER BY s.lastName")
    List<Student> getAllOfflineStudents();
}
