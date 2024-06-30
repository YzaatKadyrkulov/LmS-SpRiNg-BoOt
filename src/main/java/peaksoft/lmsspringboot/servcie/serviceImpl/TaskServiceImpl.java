package peaksoft.lmsspringboot.servcie.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.lmsspringboot.entity.Lesson;
import peaksoft.lmsspringboot.entity.Task;
import peaksoft.lmsspringboot.repository.LessonRepo;
import peaksoft.lmsspringboot.repository.TaskRepo;
import peaksoft.lmsspringboot.servcie.TaskService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final LessonRepo lessonRepo;

    @Override
    public void saveTask(Long lessonId, Task task) {
       Lesson lesson = lessonRepo.findById(lessonId).orElseThrow(
                () -> new NoSuchElementException(String.format("Lesson by id %d not found",lessonId)));
        lesson.getTasks().add(task);
        task.setLesson(lesson);
        lessonRepo.save(lesson);
        taskRepo.save(task);
    }

    @Override
    public List<Task> getAllTasks(Long lessonId) {
        return taskRepo.getAllTasks(lessonId);
    }

    @Override
    public Task getByIdTask(Long taskId) {
        return taskRepo.findById(taskId).orElseThrow(
                () -> new NoSuchElementException(String.format("Lesson by id %d not found",taskId)));

    }

    @Override
    public void updateTask(Long taskId, Task newTask) {
        Task task = getByIdTask(taskId);
        task.setTaskName(newTask.getTaskName());
        task.setTaskText(newTask.getTaskText());
        task.setDeadline(newTask.getDeadline());
        taskRepo.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepo.deleteById(taskId);
    }
}
