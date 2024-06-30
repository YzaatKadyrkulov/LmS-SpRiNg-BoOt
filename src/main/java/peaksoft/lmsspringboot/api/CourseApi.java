package peaksoft.lmsspringboot.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.lmsspringboot.entity.Course;
import peaksoft.lmsspringboot.servcie.CourseService;

import java.util.List;

@Controller
@RequestMapping("/course/{companyId}")
@RequiredArgsConstructor
public class CourseApi {
    private final CourseService courseService;

    @GetMapping()
    public String getCourses(Model model, @PathVariable Long companyId){
        List<Course> allCourses = courseService.getAllCourses(companyId);
        model.addAttribute("allCourses",allCourses);
        model.addAttribute("companyId",companyId);
        return "course/course-main";
    }

    @GetMapping("/new")
    public String creatCourse(@PathVariable Long companyId, Model model){
        model.addAttribute("companyId",companyId);
        model.addAttribute("newCourse",new Course());
        return "/course/newCourse";
    }

    @PostMapping("/save")
    public String saveCourse(@PathVariable("companyId") Long companyId,@ModelAttribute("newCourse")Course newCourse ){
        courseService.saveCourse(companyId,newCourse);
        return "redirect:/course/{companyId}";
    }

   @GetMapping("/{courseId}/getById")
    public String getCourseById(@PathVariable("courseId") Long courseId,Model model,@PathVariable("companyId") Long companyId){
        model.addAttribute("findCourse",courseService.getById(courseId));
        model.addAttribute("companyId",companyId);
        return "/course/updateCourse";
   }

   @PostMapping("/update/{courseId}")
    public String updateCourse(@PathVariable("companyId") Long companyId,
                               @ModelAttribute("findCourse") Course course,
                               @PathVariable("courseId") Long courseId){
        courseService.updateCourse(courseId,course);
        return "redirect:/course/{companyId}";
   }
   @GetMapping("/delete/{courseId}")
    public String deleteCourseById(@PathVariable("courseId") Long courseId){
        courseService.deleteCourse(courseId);
        return "redirect:/course/{companyId}";
   }
}
