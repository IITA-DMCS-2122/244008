package pl.lodz.it.jf.todolist.domain.nosql.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemDetails {

    private String uuid;

    private String title;

    private String description;

    private Integer priority;

}
