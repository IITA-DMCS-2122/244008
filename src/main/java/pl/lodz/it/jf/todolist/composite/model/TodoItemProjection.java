package pl.lodz.it.jf.todolist.composite.model;

import lombok.*;

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

}
