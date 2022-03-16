package pl.lodz.it.jf.todolist.composite.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.lodz.it.jf.todolist.composite.model.TodoItemProjection;
import pl.lodz.it.jf.todolist.domain.nosql.model.TodoItemDocument;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoItemDocumentToProjectionConverter {

    public static TodoItemProjection convertTo(TodoItemDocument source) {
        return TodoItemProjection.builder()
                .uuid(source.getUuid())
                .title(source.getTitle())
                .description(source.getDescription())
                .priority(source.getPriority())
                .build();
    }

}
