package peaksoft.lmsspringboot.servcie.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.lmsspringboot.entity.Course;
import peaksoft.lmsspringboot.entity.Group;
import peaksoft.lmsspringboot.repository.CourseRepo;
import peaksoft.lmsspringboot.repository.GroupRepo;
import peaksoft.lmsspringboot.servcie.GroupService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepo groupRepo;
    private final CourseRepo courseRepo;

    @Override
    public String createGroup(Group group, List<Long> coursesId) {
        groupRepo.save(group);
        for (Long courseId : coursesId) {
            Course course = courseRepo.findById(courseId).orElseThrow(
                    () -> new RuntimeException("Company not found with ID " + courseId));
            if (course != null) {
                group.getCourses().add(course);
                course.getGroups().add(group);
                courseRepo.save(course);
                groupRepo.save(group);
            }
        }
        return "Group created";
    }

    @Override
    public String addCourseToGroup(Long groupId, Long courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(
                () -> new RuntimeException("Company not found with ID: " + courseId));
        Group group = groupRepo.findById(groupId).orElseThrow(
                () -> new RuntimeException("Company not found with ID: " + groupRepo));
        group.getCourses().add(course);
        course.getGroups().add(group);
        courseRepo.save(course);
        groupRepo.save(group);
        return "Course added";
    }

    @Override
    public Group getGroupById(Long groupId) {
        return groupRepo.findById(groupId).orElseThrow(
                () -> new RuntimeException("Company not found with ID: " + groupRepo));
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepo.findAll();
    }

    @Override
    public List<Group> getGroupsByCompanyId(Long companyId) {
        return groupRepo.getGroupsByCompanyId(companyId);
    }

    @Override
    public String updateGroup(Long groupId, Group newGroup) {
        Group group = getGroupById(groupId);
        group.setGroupName(newGroup.getGroupName());
        group.setDescription(newGroup.getDescription());
        group.setImageLink(newGroup.getImageLink());
        groupRepo.save(group);
        return "Group updated";
    }

    @Override
    public String deleteGroupById(Long groupId) {
        Group group = getGroupById(groupId);
        if (group != null) {
            List<Course> courses = group.getCourses();
            for (Course course : courses) {
                course.getGroups().remove(group);
            }
            group.getCourses().clear();

            groupRepo.delete(group);
            return "Group deleted";
        }
        return "Group not found";
    }
}