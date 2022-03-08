package pl.lodz.it.jf.todolist.composite.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.lodz.it.jf.todolist.composite.model.TodoItemProjection;
import pl.lodz.it.jf.todolist.domain.sql.model.TodoItemEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoItemEntityToProjectionConverter {

    public static TodoItemEntity convertFrom(TodoItemProjection source) {
        return TodoItemEntity.builder()
                .uuid(source.getUuid())
                .title(source.getTitle())
                .description(source.getDescription())
                .priority(source.getPriority())
                .creationDate(source.getCreationDate())
                .build();
    }

}
