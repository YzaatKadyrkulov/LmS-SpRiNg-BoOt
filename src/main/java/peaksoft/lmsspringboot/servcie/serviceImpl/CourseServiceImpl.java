package peaksoft.lmsspringboot.servcie.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.lmsspringboot.entity.Company;
import peaksoft.lmsspringboot.entity.Course;
import peaksoft.lmsspringboot.entity.Instructor;
import peaksoft.lmsspringboot.repository.CompanyRepo;
import peaksoft.lmsspringboot.repository.CourseRepo;
import peaksoft.lmsspringboot.servcie.CourseService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;
    private final CompanyRepo companyRepo;

    @Override
    public void saveCourse(Long comId, Course course) {
        Company company = companyRepo.findById(comId).orElseThrow(
                () -> new RuntimeException("Company not found with ID: "+comId));
        course.setCompany(company);
        company.getCourses().add(course);
        courseRepo.save(course);
        companyRepo.save(company);

    }

    @Override
    public List<Course> getAllCourses(Long ComId) {
        return courseRepo.getAllCourses(ComId);
    }

    @Override
    public List<Course> getAllCourseByGroupId(Long groupId) {
        return courseRepo.getAllCourseByGroupId(groupId);
    }

    @Override
    public Course getById(Long courseId) {
        return courseRepo.findById(courseId).orElseThrow(
                () -> new RuntimeException("Company not found with ID: "+courseId));
    }

    @Override
    public void updateCourse(Long courseId, Course newCourse) {
        Course course = getById(courseId);
        course.setCourseName(newCourse.getCourseName());
        course.setDescription(newCourse.getDescription());
        course.setDateOfStart(newCourse.getDateOfStart());
        courseRepo.save(course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        Course course = getById(courseId);
        for (Instructor i : course.getInstructor()){
            if (i.getCourse().getId().equals(courseId)){
                i.setCourse(null);
            }
        }
        course.setInstructor(null);
        courseRepo.delete(course);
    }
}
