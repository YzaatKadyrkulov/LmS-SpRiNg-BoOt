package peaksoft.lmsspringboot.servcie;

import peaksoft.lmsspringboot.entity.Lesson;

import java.util.List;

public interface LessonService {
    void saveLesson(Long courseId, Lesson lesson);
    List<Lesson> getAllLesson(Long courseId);
    Lesson getByIdLesson(Long lesId);
    void  updateLesson(Long lesId,Lesson newLesson);
    void deleteLesson(Long lessId);
}
