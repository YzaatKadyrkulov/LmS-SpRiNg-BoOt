package peaksoft.lmsspringboot.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.lmsspringboot.entity.Lesson;
import peaksoft.lmsspringboot.servcie.LessonService;

@Controller
@RequestMapping("/lessons/{companyId}/{courseId}")
@RequiredArgsConstructor
public class LessonApi {
    private final LessonService lessonService;

    @GetMapping()
    public String getAllLesson(@PathVariable Long courseId, Model model,@PathVariable Long companyId) {
        model.addAttribute("allLessons", lessonService.getAllLesson(courseId));
        model.addAttribute("courseId", courseId);
        model.addAttribute("companyId", companyId);
        return "/lesson/lesson-main";
    }

    @GetMapping("/new")
    public String creatNewLesson(Model model, @PathVariable Long courseId,@PathVariable Long companyId) {
        model.addAttribute("newLesson", new Lesson());
        model.addAttribute("courseId", courseId);
        model.addAttribute("companyId", companyId);
        return "/lesson/save";
    }

    @PostMapping("/save")
    public String saveLess(@ModelAttribute("newLesson") Lesson lesson, @PathVariable Long courseId) {
        lessonService.saveLesson(courseId, lesson);
        return "redirect:/lessons/{companyId}/{courseId}";
    }

    @GetMapping("/{lessonId}/get")
    public String getLesson(@PathVariable Long lessonId, @PathVariable Long courseId, Model model,@PathVariable Long companyId) {
        model.addAttribute("findLesson", lessonService.getByIdLesson(lessonId));
        model.addAttribute("courseId", courseId);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("companyId", companyId);
        return "/lesson/update";
    }
    @PostMapping("/update/{lessonId}")
    public String updateLessonWithId(@PathVariable Long lessonId, @ModelAttribute("findLesson") Lesson lesson) {
        lessonService.updateLesson(lessonId, lesson);
        return "redirect:/lessons/{companyId}/{courseId}";
    }
    @GetMapping("/delete/{lessonId}")
    public String deleteLesson(@PathVariable Long lessonId) {
        lessonService.deleteLesson(lessonId);
        return "redirect:/lessons/{companyId}/{courseId}";
    }
}
