package pl.lodz.it.jf.todolist.composite.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemProjection {

    private String uuid;

    private String title;

    private String description;

    private Integer priority;

    private LocalDateTime creationDate;

}
