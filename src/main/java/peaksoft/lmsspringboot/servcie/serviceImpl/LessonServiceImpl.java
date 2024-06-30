package peaksoft.lmsspringboot.servcie.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.lmsspringboot.entity.Course;
import peaksoft.lmsspringboot.entity.Lesson;
import peaksoft.lmsspringboot.repository.CourseRepo;
import peaksoft.lmsspringboot.repository.LessonRepo;
import peaksoft.lmsspringboot.servcie.LessonService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepo lessonRepo;
    private final CourseRepo courseRepo;

    @Override
    public void saveLesson(Long courseId, Lesson lesson) {
        Course course = courseRepo.findById(courseId).orElseThrow(
                () -> new NoSuchElementException(String.format("Course by id %d not found", courseId)));
        course.getLessons().add(lesson);
        lesson.setCourse(course);
        courseRepo.save(course);
        lessonRepo.save(lesson);
    }

    @Override
    public List<Lesson> getAllLesson(Long courseId) {
        return lessonRepo.getAllLessonsByCourseId(courseId);
    }

    @Override
    public Lesson getByIdLesson(Long lesId) {
        return lessonRepo.findById(lesId).orElseThrow(
                () -> new NoSuchElementException(String.format("Lesson by id %d not found",lesId)));
    }

    @Override
    public void updateLesson(Long lesId, Lesson newLesson) {
        Lesson lesson = getByIdLesson(lesId);
        if (lesson != null) {
            lesson.setLessonName(newLesson.getLessonName());
        } else {
            throw new RuntimeException("lesson not found");
        }
    }

    @Override
    public void deleteLesson(Long lessId) {
        lessonRepo.deleteById(lessId);
    }
}
