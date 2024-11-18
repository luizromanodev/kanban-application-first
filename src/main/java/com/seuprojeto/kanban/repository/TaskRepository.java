package com.seuprojeto.kanban.repository;

import com.seuprojeto.kanban.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
