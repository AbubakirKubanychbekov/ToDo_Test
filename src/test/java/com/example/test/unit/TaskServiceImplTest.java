package com.example.test.unit;

import com.example.test.dto.SimpleResponse;
import com.example.test.dto.request.TaskRequest;
import com.example.test.dto.response.TaskResponse;
import com.example.test.entities.Task;
import com.example.test.repositories.TaskRepository;
import com.example.test.services.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@DataJpaTest
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    private final TaskRequest taskRequest = TaskRequest.builder()
            .description("Task for Ayil Bank")
            .done(false)
            .build();

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    @Test
    public void testGetAllTasks(){
        when(taskRepository.getAllTasks()).thenReturn(Collections.emptyList());
        List<TaskResponse> responses = taskServiceImpl.getAll();

        assertNotNull(responses);
        assertTrue(responses.isEmpty());
        Mockito.verify(taskRepository, times(1)).getAllTasks();
    }

    @Test
    public void testGetById(){
        Long taskId = 1L;
        TaskResponse taskResponse = new TaskResponse();
        when(taskRepository.getTaskById(taskId)).thenReturn(Optional.of(taskResponse));
        TaskResponse result= taskServiceImpl.getTaskById(taskId);

        verify(taskRepository, times(1)).getTaskById(taskId);
        assertEquals(taskResponse,result);
    }

    @Test
    public void testSaveTask(){
        Task task = new Task();
        when(taskRepository.save(any(Task.class))).thenReturn(new Task());
        SimpleResponse result = taskServiceImpl.save(taskRequest);

        verify(taskRepository, times(1)).save(any(Task.class));
        assertEquals(HttpStatus.OK, result.getHttpStatus());
        assertEquals("Task with id : %s success saved".formatted(task.getId()),result.getMessage());
    }

    @Test
    public void testDeleteById(){
        Long taskId = 1L;
        Task taskToDelete = new Task();
        taskToDelete.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskToDelete));
        when(taskRepository.existsById(taskId)).thenReturn(true);

        SimpleResponse result = taskServiceImpl.delete(taskId);

        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).deleteById(taskId);

        assertEquals(HttpStatus.OK, result.getHttpStatus());
        assertEquals("Task success is deleted", result.getMessage());

    }
}
