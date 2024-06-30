package peaksoft.lmsspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.lmsspringboot.entity.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {

}
