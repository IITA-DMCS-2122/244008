package pl.lodz.it.jf.todolist.domain;

import pl.lodz.it.jf.todolist.domain.elasticsearch.model.TodoItemES;
import pl.lodz.it.jf.todolist.domain.nosql.model.TodoItemDocument;
import pl.lodz.it.jf.todolist.domain.sql.model.TodoItemEntity;

public class TodoItemEntityToESConverter {

    public static TodoItemES convertTo(TodoItemEntity source) {
        return TodoItemES.builder()
                .id(source.getId())
                .uuid(source.getUuid())
                .title(source.getTitle())
                .description(source.getDescription())
                .priority(source.getPriority())
                .build();
    }

    public static TodoItemEntity convertFrom(TodoItemES source) {
        return TodoItemEntity.builder()
                .id(source.getId())
                .uuid(source.getUuid())
                .title(source.getTitle())
                .description(source.getDescription())
                .priority(source.getPriority())
                .build();
    }

}
