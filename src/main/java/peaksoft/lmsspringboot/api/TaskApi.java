package peaksoft.lmsspringboot.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.lmsspringboot.entity.Task;
import peaksoft.lmsspringboot.servcie.TaskService;

@Controller
@RequestMapping("/tasks/{companyId}/{courseId}/{lessonId}")
@RequiredArgsConstructor
public class TaskApi {
    private final TaskService taskService;

    @GetMapping()
    public String getAllLesson(@PathVariable Long lessonId, Model model,
                               @PathVariable Long companyId,@PathVariable Long courseId) {
        model.addAttribute("allTasks", taskService.getAllTasks(lessonId));
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("companyId", companyId);
        return "/task/task-main";
    }

    @GetMapping("/new")
    public String creatNewLesson(@PathVariable Long lessonId, Model model,
                                 @PathVariable Long companyId,@PathVariable Long courseId) {
        model.addAttribute("newTask", new Task());
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("companyId", companyId);
        return "/task/save";
    }

    @PostMapping("/save")
    public String saveLess(@ModelAttribute("newTask") Task task, @PathVariable Long lessonId) {
        taskService.saveTask(lessonId, task);
        return "redirect:/tasks/{companyId}/{courseId}/{lessonId}";
    }

    @GetMapping("/{taskId}/get")
    public String getLesson(@PathVariable Long lessonId, @PathVariable Long taskId, Model model,
                            @PathVariable Long companyId,@PathVariable Long courseId) {
        model.addAttribute("newTask", taskService.getByIdTask(taskId));
        model.addAttribute("taskId", taskId);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("companyId", companyId);
        return "/task/update";
    }

    @PostMapping("/update/{taskId}")
    public String updateLessonWithId(@PathVariable Long taskId, @ModelAttribute("newTask") Task task) {
       taskService.updateTask(taskId,task);
        return "redirect:/tasks/{companyId}/{courseId}/{lessonId}";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteLesson(@PathVariable Long taskId) {
       taskService.deleteTask(taskId);
        return "redirect:/tasks/{companyId}/{courseId}/{lessonId}";
    }
}
