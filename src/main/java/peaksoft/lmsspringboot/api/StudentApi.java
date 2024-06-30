package peaksoft.lmsspringboot.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.lmsspringboot.entity.Student;
import peaksoft.lmsspringboot.servcie.StudentService;


@Controller
@RequestMapping("/students/{companyId}")
@RequiredArgsConstructor
public class StudentApi {
    private final StudentService studentService;

    @GetMapping("/{groupId}")
    public String getAllStudentInCompany(@PathVariable Long companyId, Model model, @PathVariable Long groupId) {
        model.addAttribute("allStudents", studentService.getAllByStudentsByGroupId(groupId));
        model.addAttribute("groupId", groupId);
        model.addAttribute("companyId", companyId);
        return "/student/student-main";
    }

    @GetMapping("/{groupId}/new")
    public String creatNewStudent(@PathVariable Long groupId, @PathVariable Long companyId, Model model) {
        model.addAttribute("newStudent", new Student());
        model.addAttribute("groupId", groupId);
        model.addAttribute("companyId", companyId);
        return "/student/addStudent";
    }

    @PostMapping("/{groupId}/save")
    public String saveNewStudent(@ModelAttribute("newStudent") Student student, @PathVariable Long groupId, @PathVariable Long companyId) {
        studentService.createStudent(student, groupId);
        return "redirect:/students/{companyId}/{groupId}";
    }

    @GetMapping("/{groupId}/{studentId}/get")
    public String getStudent(@PathVariable Long studentId, Model model, @PathVariable Long groupId, @PathVariable Long companyId) {
        model.addAttribute("findStudent", studentService.getStudentById(studentId));
        model.addAttribute("groupId", groupId);
        model.addAttribute("companyId", companyId);
        return "/student/updateStudent";
    }

    @GetMapping("/{groupId}/{studentId}/more")
    public String getMore(@PathVariable Long studentId, Model model, @PathVariable Long groupId, @PathVariable Long companyId){
        model.addAttribute("findStudent", studentService.getStudentById(studentId));
        model.addAttribute("groupId", groupId);
        model.addAttribute("companyId", companyId);
        return "/student/more";
    }

    @GetMapping("/{groupId}/{studentId}/more1")
    public String getMore1(@PathVariable Long studentId, Model model, @PathVariable Long groupId, @PathVariable Long companyId){
        model.addAttribute("findStudent", studentService.getStudentById(studentId));
        model.addAttribute("groupId", groupId);
        model.addAttribute("companyId", companyId);
        return "/student/count/company-students-more";
    }

    @GetMapping("/{groupId}/{studentId}/more2")
    public String getMore2(@PathVariable Long studentId, Model model, @PathVariable Long groupId, @PathVariable Long companyId){
        model.addAttribute("findStudent", studentService.getStudentById(studentId));
        model.addAttribute("groupId", groupId);
        model.addAttribute("companyId", companyId);
        return "/student/count/online-students-more";
    }

    @GetMapping("/{groupId}/{studentId}/more3")
    public String getMore3(@PathVariable Long studentId, Model model, @PathVariable Long groupId, @PathVariable Long companyId){
        model.addAttribute("findStudent", studentService.getStudentById(studentId));
        model.addAttribute("groupId", groupId);
        model.addAttribute("companyId", companyId);
        return "/student/count/offline-students-more";
    }


    @PostMapping("/{groupId}/{studentId}/update")
    public String updateStudent(@PathVariable Long studentId ,@ModelAttribute("findStudent") Student student ){
        studentService.updateStudent(studentId,student);
        return "redirect:/students/{companyId}/{groupId}";
    }
    @GetMapping("/{groupId}/{studentId}/delete")
    public String deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudentById(studentId);
        return "redirect:/students/{companyId}/{groupId}";
    }
    @GetMapping("/getAllFromCompany")
    public String getAll(@PathVariable Long companyId, Model model){
        model.addAttribute("allStudents", studentService.getAllByStudentsByCompanyId(companyId));
        model.addAttribute("companyId", companyId);
        return "student/count/company-students";
    }
    @GetMapping("/get/all")
    public String countStudent( @PathVariable Long companyId, Model model){
        model.addAttribute("companyId", companyId);
        return "student/count/count-main";
    }

    @GetMapping("/online")
    public String getOnlineStudents(@PathVariable Long companyId,Model model){
        model.addAttribute("all",studentService.getAllOnlineStudents());
        model.addAttribute("comId",companyId);
        return "/student/count/online";
    }
    @GetMapping("/offline")
    public String getOfflineStudents(@PathVariable Long companyId,Model model){
        model.addAttribute("all",studentService.getAllOfflineStudents());
        model.addAttribute("comId",companyId);
        return "/student/count/offline";
    }
    @GetMapping("/{studentId}/get")
    public String getStudentToUpdate(@PathVariable Long studentId, Model model, @PathVariable Long groupId, @PathVariable Long companyId) {
        model.addAttribute("findStudent", studentService.getStudentById(studentId));
        model.addAttribute("groupId", groupId);
        model.addAttribute("companyId", companyId);
        return "/student/updateStudent";
    }
    @PostMapping("/{studentId}/update")
    public String updateStudentToNewStudent(@PathVariable Long studentId , @ModelAttribute("findStudent") Student student, @PathVariable String companyId){
        studentService.updateStudent(studentId,student);
        return "redirect:/students/{companyId}/{groupId}";
    }
    @GetMapping("/{studentId}/delete")
    public String removeStudent(@PathVariable Long studentId){
        studentService.deleteStudentById(studentId);
        return "redirect:/students/{companyId}/{groupId}";
    }

}
