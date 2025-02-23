package com.firozkhan.todo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firozkhan.todo.models.Task;

/**
 * TaskRepository
 */
@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

} 
