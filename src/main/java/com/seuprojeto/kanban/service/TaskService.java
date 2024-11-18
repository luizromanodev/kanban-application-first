package com.seuprojeto.kanban.service;

import com.seuprojeto.kanban.model.Task;
import com.seuprojeto.kanban.model.TaskStatus;
import com.seuprojeto.kanban.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.seuprojeto.kanban.model.TaskStatus.DONE;
import static com.seuprojeto.kanban.model.TaskStatus.IN_PROGRESS;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        task.setStatus(TaskStatus.TO_DO);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setPriority(updatedTask.getPriority());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task moveTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        switch (task.getStatus()) {
            case TO_DO -> task.setStatus(IN_PROGRESS);
            case IN_PROGRESS -> task.setStatus(DONE);
            case DONE -> throw new IllegalStateException("Task already completed");
        }
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
