package com.example.test.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_gen")
    @SequenceGenerator(
            name = "task_gen",
            sequenceName = "task_seq",
            allocationSize = 1)
    Long id;
    String description;
    boolean done;
}
