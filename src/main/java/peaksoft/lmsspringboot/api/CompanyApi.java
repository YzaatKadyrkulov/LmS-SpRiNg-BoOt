package peaksoft.lmsspringboot.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.lmsspringboot.entity.Company;
import peaksoft.lmsspringboot.servcie.CompanyService;

@Controller
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyApi {
    private final CompanyService companyService;

    @GetMapping("/allCompany")
    public String getAllCompanies(Model model) {
        model.addAttribute("allCompanies", companyService.getAllCompany());
        return "mainPage";
    }


    @GetMapping("/new")
    public String addCompany(Model model) {
        model.addAttribute("newCompany", new Company());
        return "/newCompany";
    }

    @PostMapping("/save")
    private String saveCompany(@ModelAttribute("newCompany") Company company) {
        companyService.saveCompany(company);
        return "redirect:/companies/allCompany";
    }

    @GetMapping("/{id}/delete")
    public String deleteCompany(@PathVariable("id") Long companyId) {
        companyService.deleteCompanyById(companyId);
        return "redirect:/companies/allCompany";
    }

    @GetMapping("/{id}/update")
    public String getByIdToUpdate(Model model, @PathVariable("id") Long comId) {
        model.addAttribute("upCompany", companyService.getById(comId));
        return "/update";
    }

    @PostMapping("/{id}/updateCompany")
    public String update(@ModelAttribute("upCompany") Company company, @PathVariable Long id) {
        companyService.updateById(id, company);
        return "redirect:/companies/allCompany";
    }

    @GetMapping("/lets/{companyId}")
    public String getInfo(@PathVariable Long companyId, Model model) {
        model.addAttribute("company", companyService.getById(companyId));
        return "more-company";
    }

}