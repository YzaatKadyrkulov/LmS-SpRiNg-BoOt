package peaksoft.lmsspringboot.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.lmsspringboot.entity.Group;
import peaksoft.lmsspringboot.servcie.CourseService;
import peaksoft.lmsspringboot.servcie.GroupService;

import java.util.List;


@Controller
@RequestMapping("/groups/{companyId}")
@RequiredArgsConstructor
public class GroupApi {
    private final GroupService groupService;
    private final CourseService courseService;

    @GetMapping
    public String getAllFromCompany(@PathVariable Long companyId, Model model) {
        model.addAttribute("companyGroups", groupService.getGroupsByCompanyId(companyId));
        model.addAttribute("companyId", companyId);
        return "group/group-main";
    }

    @GetMapping("/new")
    public String createGroupForm(@PathVariable Long companyId, Model model) {
        model.addAttribute("companyId", companyId);
        model.addAttribute("newGroup", new Group());
        model.addAttribute("allCourse", courseService.getAllCourses(companyId));
        return "group/save";
    }

    @PostMapping("/save")
    public String saveGroup(@PathVariable Long companyId,
                            @ModelAttribute("newGroup") Group group,
                            @RequestParam("courseIds") List<Long> courseIds) {
        groupService.createGroup(group, courseIds);
        return "redirect:/groups/" + companyId;
    }

    @GetMapping("/{groupId}/get")
    public String get(@PathVariable Long groupId, @PathVariable Long companyId, Model model) {
        model.addAttribute("upGroup", groupService.getGroupById(groupId));
        model.addAttribute("allCourses",courseService.getAllCourses(companyId));
        model.addAttribute("companyId", companyId);
        model.addAttribute("groupId", groupId);
        return "/group/update";
    }

    @PostMapping("/update/{groupId}")
    public String updateGroup(@PathVariable Long groupId, @ModelAttribute("upGroup") Group group) {
        groupService.updateGroup(groupId, group);
        return "redirect:/groups/{companyId}";
    }

    @GetMapping("/delete/{groupId}")
    public String deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroupById(groupId);
        return "redirect:/groups/{companyId}";
    }

    @GetMapping("/{groupId}/courses")
    public String getAllCoursesInGroup(@PathVariable Long groupId,Model model,@PathVariable Long companyId){
        model.addAttribute("allCourse",courseService.getAllCourseByGroupId(groupId));
        model.addAttribute("comId",companyId);
        return "group/groups-courses";
    }




}
