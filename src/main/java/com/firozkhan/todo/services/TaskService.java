package com.firozkhan.todo.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.firozkhan.todo.models.Task;
import com.firozkhan.todo.repositorys.TaskRepository;

/**
 * TaskService
 */
@Service
public class TaskService {

  private final TaskRepository taskRepository;
  private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<Task> getAll() {
    return taskRepository.findAll();
  }

  public Task get(Long id) {
    return taskRepository.findById(id).orElse(null);
  }

  public Task create(Task task) {
    if (task == null) {
      return null;
    }
    Task taskCreated = taskRepository.save(task);

    logger.info("Task created: {}", taskCreated);

    return taskCreated;
  }

  public Task update(Long id, Task task) {
    Task taskToUpdate = taskRepository.findById(id).orElse(null);

    if (taskToUpdate == null) {
      return null;
    }

    taskToUpdate.setTitle(task.getTitle());
    taskToUpdate.setDescription(task.getDescription());
    taskToUpdate.setCompleted(task.isCompleted());

    return taskRepository.save(taskToUpdate);
  }

  public void delete(Long id) {
    taskRepository.deleteById(id);
  }
}
