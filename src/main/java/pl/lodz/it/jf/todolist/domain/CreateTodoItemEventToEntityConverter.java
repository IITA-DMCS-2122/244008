package pl.lodz.it.jf.todolist.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.lodz.it.jf.todolist.domain.nosql.model.CreateTodoItemEvent;

import pl.lodz.it.jf.todolist.domain.sql.model.TodoItemEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateTodoItemEventToEntityConverter {

    public static TodoItemEntity convertTo(CreateTodoItemEvent source) {
        return TodoItemEntity.builder()
                .uuid(source.getDetails().getUuid())
                .title(source.getDetails().getTitle())
                .description(source.getDetails().getDescription())
                .priority(source.getDetails().getPriority())
                .build();
    }

}
