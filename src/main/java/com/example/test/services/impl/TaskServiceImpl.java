package com.example.test.services.impl;

import com.example.test.dto.SimpleResponse;
import com.example.test.dto.request.TaskRequest;
import com.example.test.dto.response.TaskResponse;
import com.example.test.entities.Task;
import com.example.test.exceptions.BadCredentialException;
import com.example.test.exceptions.NotFoundException;
import com.example.test.repositories.TaskRepository;
import com.example.test.services.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    /**
     * Получить все задачи.
     *
     * @return Возвращает список задач.
     */
    @Override
    public List<TaskResponse> getAll() {
        return taskRepository.getAllTasks();
    }


    /**
     * Сохранить новую задачу.
     *
     * @param taskRequest Запрос на создание задачи.
     * @return Ответ о статусе сохранения задачи.
     */
    @Override
    public SimpleResponse save(TaskRequest taskRequest) {
        Task task = new Task();
        task.setDescription(taskRequest.getDescription());
        task.setDone(taskRequest.isDone());
        log.info("Task success saved");
        taskRepository.save(task);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Task with id : %s is saved".formatted(task.getId()))
                .build();
    }


    /**
     * Получить задачу по идентификатору.
     *
     * @param id Идентификатор задачи.
     * @return Задача.
     * @throws NotFoundException если задача с указанным идентификатором не найдена.
     */
    @Override
    public TaskResponse getTaskById(Long id) {
        return taskRepository.getTaskById(id).orElseThrow(()->
                new NotFoundException("Task with id : %s not found".formatted(id)));
    }


    /**
     * Обновить задачу по идентификатору.
     *
     * @param id идентификатор задачи.
     * @param taskRequest Запрос на обновление задачи.
     * @return Ответ о статусе обновления задачи.
     * @throws NotFoundException если задача с идентификатором не найден.
     */
    @Override
    public SimpleResponse update(Long id, TaskRequest taskRequest) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Task with id : %s not found".formatted(id)));
        if (taskRequest.getDescription() != null){
            task.setDescription(taskRequest.getDescription());
        }
        task.setDone(taskRequest.isDone());
        log.info("Task success is updated");
        taskRepository.save(task);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Task with id : %s is updated".formatted(id))
                .build();
    }


    /**
     * Удалить задачу по идентификатору.
     *
     * @param id Идентификатор задачи.
     * @return Ответ о статусе обновления задачи.
     * @throws BadCredentialException если задача с идентификатором пуст.
     */
    @Override
    public SimpleResponse delete(Long id) {
        if (!taskRepository.existsById(id)){
            throw new BadCredentialException("Task with id : %s not exist".formatted(id));
        }
        taskRepository.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Task with id : %s is deleted".formatted(id))
                .build();
    }
}