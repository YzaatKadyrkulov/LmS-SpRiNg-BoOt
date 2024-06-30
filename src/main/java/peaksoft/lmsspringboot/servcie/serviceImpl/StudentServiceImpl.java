package peaksoft.lmsspringboot.servcie.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.lmsspringboot.entity.Group;
import peaksoft.lmsspringboot.entity.Student;
import peaksoft.lmsspringboot.repository.GroupRepo;
import peaksoft.lmsspringboot.repository.StudentRepo;
import peaksoft.lmsspringboot.servcie.StudentService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final GroupRepo groupRepo;

    @Override
    public String createStudent(Student student, Long groupId) {
        Group group = groupRepo.findById(groupId).orElseThrow(
                () -> new RuntimeException("Company not found with ID: "+groupRepo));
        student.setGroup(group);
        group.getStudents().add(student);
        studentRepo.save(student);
        groupRepo.save(group);
        return "successfully added";
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepo.findById(studentId).orElseThrow(
                () -> new RuntimeException("Student not found with ID: "+studentId));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public String updateStudent(Long studentId, Student newStudent) {
        Student student = getStudentById(studentId);
        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
        student.setEmail(newStudent.getEmail());
        student.setPhoneNumber(newStudent.getPhoneNumber());
        student.setFormatStudy(newStudent.getFormatStudy());
        studentRepo.save(student);
        return "Student updated";
    }

    @Override
    public String deleteStudentById(Long studentId) {
        studentRepo.deleteById(studentId);
        return "Student deleted";
    }

    @Override
    public String assignStudentToGroup(Long studentId, Long groupId) {
        Student student = getStudentById(studentId);
        Group group = groupRepo.findById(groupId).orElseThrow(
                () -> new RuntimeException("Company not found with ID: "+groupRepo));
        student.setGroup(group);
        group.getStudents().add(student);
        studentRepo.save(student);
        groupRepo.save(group);
        return "Student assigned to group";
    }

    @Override
    public List<Student> getAllByStudentsByCompanyId(Long comId) {
        return studentRepo.getAllByStudentsByCompanyId(comId);
    }

    @Override
    public List<Student> getAllByStudentsByGroupId(Long groupId) {
        return studentRepo.getAllByStudentsByGroupId(groupId);
    }

    @Override
    public List<Student> getAllOnlineStudents() {
        return studentRepo.getAllOnlineStudents();
    }

    @Override
    public List<Student> getAllOfflineStudents() {
        return studentRepo.getAllOfflineStudents();
    }
}
