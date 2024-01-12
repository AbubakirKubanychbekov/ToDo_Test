package com.example.test.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter @Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskRequest {
    String description;
    boolean done;
}
