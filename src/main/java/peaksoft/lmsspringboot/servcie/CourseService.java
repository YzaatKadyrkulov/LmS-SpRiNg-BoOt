package peaksoft.lmsspringboot.servcie;

import peaksoft.lmsspringboot.entity.Course;

import java.util.List;

public interface CourseService {
    void  saveCourse(Long comId, Course course);
    List<Course> getAllCourses(Long ComId);
    List<Course> getAllCourseByGroupId(Long groupId);
    Course getById(Long courseId);
    void updateCourse(Long courseId,Course newCourse);
    void  deleteCourse(Long courseId);
}
