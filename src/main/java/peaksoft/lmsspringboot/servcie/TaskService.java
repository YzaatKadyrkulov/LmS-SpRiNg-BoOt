package peaksoft.lmsspringboot.servcie;

import peaksoft.lmsspringboot.entity.Task;

import java.util.List;

public interface TaskService {
    void saveTask(Long lessonId, Task task);
    List<Task> getAllTasks(Long lessonId);
    Task getByIdTask(Long taskId);
    void  updateTask(Long taskId,Task newTask);
    void deleteTask(Long taskId);
}
