package peaksoft.lmsspringboot.servcie;

import peaksoft.lmsspringboot.entity.Student;

import java.util.List;

public interface StudentService {
    String createStudent(Student student, Long groupId);

    Student getStudentById(Long studentId);

    List<Student> getAllStudents();

    String updateStudent(Long studentId, Student newStudent);

    String deleteStudentById(Long studentId);

    String assignStudentToGroup(Long studentId, Long groupId);
    List<Student> getAllByStudentsByCompanyId(Long comId);
    List<Student> getAllByStudentsByGroupId(Long comId);
    List<Student> getAllOnlineStudents();
    List<Student> getAllOfflineStudents();
}
