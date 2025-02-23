package com.firozkhan.todo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firozkhan.todo.models.Task;
import com.firozkhan.todo.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TaskController
 */
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

  private final TaskService taskService;
  private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

  public TaskController(TaskService taskService){
    this.taskService = taskService;
  }

  @GetMapping
  public ResponseEntity<List<Task>> getAll(){
   return ResponseEntity.ok(taskService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Task> get(@PathVariable Long id){
    return ResponseEntity.ok(taskService.get(id));
  }

  @PostMapping
  public ResponseEntity<Task> create(@RequestBody Task task){
    
    logger.info("Received Task: {}", task);

    Task taskCreated = taskService.create(task);
    if (taskCreated == null) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(taskCreated);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Task> update(@PathVariable Long id,@RequestBody Task task){
    return ResponseEntity.ok(taskService.update(id, task));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id){
    taskService.delete(id);
    return ResponseEntity.ok().build();
  }
  
}
