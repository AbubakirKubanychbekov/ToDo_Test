package com.example.test.services;

import com.example.test.dto.SimpleResponse;
import com.example.test.dto.request.TaskRequest;
import com.example.test.dto.response.TaskResponse;
import java.util.List;

public interface TaskService {
    List<TaskResponse> getAll();

    SimpleResponse save(TaskRequest taskRequest);

    TaskResponse getTaskById(Long id);

    SimpleResponse update(Long id, TaskRequest taskRequest);

    SimpleResponse delete(Long id);
}
