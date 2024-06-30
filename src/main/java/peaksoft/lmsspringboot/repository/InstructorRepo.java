package peaksoft.lmsspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.lmsspringboot.entity.Group;
import peaksoft.lmsspringboot.entity.Instructor;

import java.util.List;
@Repository
public interface InstructorRepo extends JpaRepository<Instructor,Long> {
    @Query("select i from Instructor i join i.course c where c.id = :id")
    List<Instructor> getAllInstructorsByCourseId(@Param("id") Long courseId);
    @Query("select i from Instructor i join i.companies c where c.id = :comId")
    List<Instructor> getAllInstructorsByComId(@Param("comId") Long comId);
    @Modifying
    @Transactional
    @Query("delete from Instructor i where i.id = :insId and :comId member of i.companies")
    void deleteInstructorFromCompany(@Param("insId") Long insId, @Param("comId") Long comId);


}
