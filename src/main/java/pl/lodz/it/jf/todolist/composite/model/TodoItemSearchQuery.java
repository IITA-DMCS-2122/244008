package pl.lodz.it.jf.todolist.composite.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemSearchQuery {

    private String description;

}
