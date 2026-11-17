package com.example.todo.service;

import com.example.todo.entity.Task;
import com.example.todo.exception.ResourceNotFoundException;
import com.example.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        task.setId(null); // ensure a new row is always created
        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    public Task updateTask(Long id, Task details) {
        Task task = getTaskById(id);
        task.setTitle(details.getTitle());
        task.setDescription(details.getDescription());
        if (details.getStatus() != null) {
            task.setStatus(details.getStatus());
        }
        if (details.getPriority() != null) {
            task.setPriority(details.getPriority());
        }
        task.setDueDate(details.getDueDate());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    public Task completeTask(Long id) {
        Task task = getTaskById(id);
        task.setStatus(Task.Status.COMPLETED);
        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public List<Task> searchByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }

    @Transactional(readOnly = true)
    public List<Task> filterByStatus(Task.Status status) {
        return taskRepository.findByStatus(status);
    }
}
