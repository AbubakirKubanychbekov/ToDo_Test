package com.example.test.repositories;

import com.example.test.dto.response.TaskResponse;
import com.example.test.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("select new com.example.test.dto.response.TaskResponse(t.id,t.description,t.done) from Task t")
    List<TaskResponse> getAllTasks();

    @Query("select new com.example.test.dto.response.TaskResponse(t.id,t.description,t.done) from Task t where t.id = :id")
    Optional<TaskResponse> getTaskById(Long id);
}
