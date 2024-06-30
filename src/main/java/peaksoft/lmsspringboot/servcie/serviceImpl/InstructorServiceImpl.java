package peaksoft.lmsspringboot.servcie.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.lmsspringboot.entity.Company;
import peaksoft.lmsspringboot.entity.Course;
import peaksoft.lmsspringboot.entity.Instructor;
import peaksoft.lmsspringboot.repository.CompanyRepo;
import peaksoft.lmsspringboot.repository.CourseRepo;
import peaksoft.lmsspringboot.repository.InstructorRepo;
import peaksoft.lmsspringboot.servcie.InstructorService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepo instructorRepo;
    private final CompanyRepo companyRepo;
    private final CourseRepo courseRepo;

    @Override
    public void saveInstructor(Instructor instructor) {
        instructorRepo.save(instructor);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepo.findById(id).orElseThrow(
                () -> new NoSuchElementException(String.format("Instructor by id %d not found", id)));
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepo.findAll();
    }

    @Override
    public List<Instructor> getAllInstructorsByCourseId(Long courseId) {
        return instructorRepo.getAllInstructorsByCourseId(courseId);
    }

    @Override
    public void updateInstructor(Long insId, Instructor newInstructor) {
        Instructor instructor = getInstructorById(insId);
        if (instructor != null) {
            instructor.setFirstName(newInstructor.getFirstName());
            instructor.setLastName(newInstructor.getLastName());
            instructor.setSpecialization(newInstructor.getSpecialization());

            instructorRepo.save(instructor);
        }
    }

    @Override
    public void deleteById(Long insId) {
        instructorRepo.deleteById(insId);
    }

    @Override
    public void assignInstructorToCompany(List<Long> insId, Long comId) {
        Company company = companyRepo.findById(comId).orElseThrow(
                () -> new NoSuchElementException(String.format("Company by id %d not found", comId)));
        for (Long inId : insId) {
            Instructor instructor = getInstructorById(inId);
            if (instructor != null) {
                company.getInstructors().add(instructor);
                instructor.getCompanies().add(company);
                instructorRepo.save(instructor);
            }
        }
        companyRepo.save(company);
    }

    @Override
    public void addInstructorToCourse(Long insId, Long courseId) {
        Instructor instructor = getInstructorById(insId);
        Course course = courseRepo.findById(courseId).orElseThrow(
                () -> new NoSuchElementException(String.format("Course by id %d not found", courseId)));
        course.getInstructor().add(instructor);
        instructor.setCourse(course);
        instructorRepo.save(instructor);
        courseRepo.save(course);
    }

    @Override
    public List<Instructor> getAllInstructorsByComId(Long comId) {
        return instructorRepo.getAllInstructorsByComId(comId);
    }

    @Override
    public void deleteInstructorFromCompany(Long insId, Long comId) {
        instructorRepo.deleteInstructorFromCompany(insId, comId);
    }

    @Override
    public void deleteInstructorFromCourse(Long insId, Long courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(
                () -> new NoSuchElementException(String.format("Course by id %d not found", courseId)));
        Instructor instructor = getInstructorById(insId);
        if (course.getId().equals(courseId)) {
            course.getInstructor().removeIf
                    (instructor1 -> instructor1.getId().equals(insId));
            instructor.setCourse(null);
            instructorRepo.save(instructor);
            courseRepo.save(course);
        }
    }
}
