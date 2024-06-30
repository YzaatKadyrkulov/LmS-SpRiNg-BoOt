package peaksoft.lmsspringboot.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.lmsspringboot.entity.Instructor;
import peaksoft.lmsspringboot.servcie.InstructorService;

import java.util.List;

@Controller
@RequestMapping("/instructors")
@RequiredArgsConstructor
public class InstructorApi {
    private final InstructorService instructorService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("allInstructor", instructorService.getAllInstructors());
        return "instructor/instructor-main";
    }

    @GetMapping("/new")
    public String creatIns(Model model) {
        model.addAttribute("newInstructor", new Instructor());
        return "/instructor/save";
    }

    @PostMapping("/save")
    public String saveInstructor(@ModelAttribute("newInstructor") Instructor instructor) {
        instructorService.saveInstructor(instructor);
        return "redirect:/instructors";
    }

    @GetMapping("/{instructorId}/get")
    public String getIns(@PathVariable Long instructorId, Model model) {
        model.addAttribute("ins", instructorService.getInstructorById(instructorId));
        model.addAttribute("insId", instructorId);
        return "instructor/update";
    }

    @PostMapping("/update/{instructorId}")
    public String update(@ModelAttribute("ins") Instructor instructor, @PathVariable Long instructorId) {
        instructorService.updateInstructor(instructorId, instructor);
        return "redirect:/instructors";
    }

    @GetMapping("/delete/{instructorId}")
    public String delete(@PathVariable Long instructorId) {
        instructorService.deleteById(instructorId);
        return "redirect:/instructors";
    }

    @GetMapping("/instructors/getFrom/{companyId}")
    public String assignInstructor(@PathVariable Long companyId, Model model) {
        model.addAttribute("allInstructors", instructorService.getAllInstructors());
        model.addAttribute("comId", companyId);
        return "/assign/ins-com";
    }

    @PostMapping("/{companyId}/addInstructors")
    public String addIns(@PathVariable Long companyId, @RequestParam List<Long> instructorIds) {
        instructorService.assignInstructorToCompany(instructorIds, companyId);
        return "redirect:/instructors/{companyId}/getAll";
    }

    @GetMapping("/{companyId}/getAll")
    public String getAllInFromCompany(@PathVariable Long companyId, Model model) {
        model.addAttribute("allIn", instructorService.getAllInstructorsByComId(companyId));
        model.addAttribute("comId", companyId);
        return "/assign/com/allComIns";
    }

    @GetMapping("/{companyId}/getAllInstructorCompany/{courseId}")
    private String getAllInsFromCourse(@PathVariable Long courseId, @PathVariable Long companyId, Model model) {
        model.addAttribute("allInsInCompany", instructorService.getAllInstructorsByComId(companyId));
        model.addAttribute("courseId", courseId);
        model.addAttribute("companyId", companyId);
        return "/assign/cour/ins-course";
    }

    @PostMapping("/{companyId}/{courseId}/AddInsToCourse")
    public String assignInsToCourse(@PathVariable Long companyId, @PathVariable Long courseId, @RequestParam List<Long> instructorIds) {
        for (Long instructorId : instructorIds) {
            instructorService.addInstructorToCourse(instructorId, courseId);
        }
        return "redirect:/instructors/" + companyId + "/" + courseId + "/getAll";
    }

    @GetMapping("/{companyId}/{courseId}/getAll")
    public String getAllFromCourse(@PathVariable Long companyId, @PathVariable Long courseId, Model model) {
        model.addAttribute("allIns", instructorService.getAllInstructorsByCourseId(courseId));
        model.addAttribute("courseId", courseId);
        model.addAttribute("comId", companyId);
        return "/assign/cour/allCourIn";
    }

    @GetMapping("/{companyId}/{instructorId}")
    public String deleteInsFromCom(@PathVariable Long companyId, @PathVariable Long instructorId){
        instructorService.deleteInstructorFromCompany(instructorId,companyId);
        return "redirect:/instructors/{companyId}/getAll";
    }

    @GetMapping("/{companyId}/{courseId}/{instructorId}")
    public String deleteInsFromCourse(@PathVariable Long courseId, @PathVariable Long instructorId,@PathVariable Long companyId){
        instructorService.deleteInstructorFromCourse(instructorId,courseId);
        return  "redirect:/instructors/" + companyId + "/" + courseId + "/getAll";
    }

}


