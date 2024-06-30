package peaksoft.lmsspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.lmsspringboot.entity.Course;
import peaksoft.lmsspringboot.entity.Group;

import java.util.List;
@Repository
public interface GroupRepo extends JpaRepository<Group,Long> {


 @Query("select g from Group g " +
         "join g.courses c " +
         "join c.company co " +
         "where co.id = :companyId")
 List<Group> getGroupsByCompanyId(@Param("companyId") Long companyId);



}
