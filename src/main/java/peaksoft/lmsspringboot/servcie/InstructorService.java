package peaksoft.lmsspringboot.servcie;

import peaksoft.lmsspringboot.entity.Instructor;

import java.util.List;

public interface InstructorService {
    void saveInstructor(Instructor instructor);
    Instructor getInstructorById(Long id);
    List<Instructor> getAllInstructors();
    List<Instructor>getAllInstructorsByCourseId(Long courseId);
    void updateInstructor(Long insId,Instructor newInstructor);
    void deleteById(Long insId);
    void assignInstructorToCompany(List<Long> insId,Long comId);
    void addInstructorToCourse(Long insId,Long courseId);
    List<Instructor>getAllInstructorsByComId(Long comId);
    void deleteInstructorFromCompany(Long insId,Long comId);
    void deleteInstructorFromCourse(Long insId,Long courseId);
}
