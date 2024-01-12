package com.example.test.api;

import com.example.test.dto.SimpleResponse;
import com.example.test.dto.request.TaskRequest;
import com.example.test.dto.response.TaskResponse;
import com.example.test.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Tag(name = "Task API")
public class TaskController {

    private final TaskService taskService;

    @PermitAll
    @GetMapping()
    @Operation(summary = "Получить все задачи")
    public List<TaskResponse> getAll() {
        return taskService.getAll();
    }


    @PermitAll
    @PostMapping
    @Operation(summary = "Сохранить задачи",
            description = "Добавляет новые задачи в список.")
    public SimpleResponse save(@RequestBody TaskRequest taskRequest) {
        return taskService.save(taskRequest);
    }


    @PermitAll
    @GetMapping("/{id}")
    @Operation(summary = "Получить задачу по идентификатору",
            description = "Возвращает информацию о задаче с указанным идентификатором.")
    public TaskResponse getById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }


    @PermitAll
    @PutMapping("/{id}")
    @Operation(summary = "Обновить задачу по идентификатору",
            description = "Обновляет информацию о задаче с указанным идентификатором.")
    public SimpleResponse update(@PathVariable Long id,
                                 @RequestBody TaskRequest taskRequest) {
        return taskService.update(id, taskRequest);
    }


    @PermitAll
    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить задачу по идентификатору",
            description = "Удаляет задачу с указанным идентификатором.")
    public SimpleResponse delete(@PathVariable Long id) {
        return taskService.delete(id);
    }
}
