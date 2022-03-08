package pl.lodz.it.jf.todolist.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.lodz.it.jf.todolist.domain.nosql.model.TodoItemDocument;
import pl.lodz.it.jf.todolist.domain.sql.model.TodoItemEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoItemEntityToDocumentConverter {

    public static TodoItemDocument convertTo(TodoItemEntity source) {
        return TodoItemDocument.builder()
                .id(source.getId())
                .uuid(source.getUuid())
                .title(source.getTitle())
                .description(source.getDescription())
                .priority(source.getPriority())
                .creationDate(source.getCreationDate())
                .build();
    }

    public static TodoItemEntity convertFrom(TodoItemDocument source) {
        return TodoItemEntity.builder()
                .id(source.getId())
                .uuid(source.getUuid())
                .title(source.getTitle())
                .description(source.getDescription())
                .priority(source.getPriority())
                .creationDate(source.getCreationDate())
                .build();
    }

}
