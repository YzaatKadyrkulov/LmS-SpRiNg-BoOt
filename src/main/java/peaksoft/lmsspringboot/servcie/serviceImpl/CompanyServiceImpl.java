package peaksoft.lmsspringboot.servcie.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.lmsspringboot.entity.Company;
import peaksoft.lmsspringboot.repository.CompanyRepo;
import peaksoft.lmsspringboot.servcie.CompanyService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepo companyRepo;

    @Override
    public void saveCompany(Company company) {
       companyRepo.save(company);
    }

    @Override
    public Company getById(Long id) {
        return companyRepo.findById(id).orElseThrow(()->new RuntimeException("Company by id "+id+" not found"));
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepo.findAll();
    }

    @Override
    public void updateById(Long id, Company newCompany) {
        Company company = getById(id);
        company.setName(newCompany.getName());
        company.setImageLink(newCompany.getImageLink());
        company.setAddress(newCompany.getAddress());
        company.setCountry(newCompany.getCountry());
        companyRepo.save(company);
    }

    @Override
    public void deleteCompanyById(Long id) {
        companyRepo.deleteById(id);
    }
}
