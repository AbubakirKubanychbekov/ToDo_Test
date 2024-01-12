package com.example.test.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter @Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskResponse {
    Long id;
    String description;
    boolean done;

    public TaskResponse() {
    }

    public TaskResponse(Long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }
}
