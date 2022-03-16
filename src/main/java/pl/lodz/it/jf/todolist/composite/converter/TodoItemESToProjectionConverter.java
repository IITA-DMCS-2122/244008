package pl.lodz.it.jf.todolist.composite.converter;

import pl.lodz.it.jf.todolist.composite.model.TodoItemProjection;
import pl.lodz.it.jf.todolist.domain.elasticsearch.model.TodoItemES;

public class TodoItemESToProjectionConverter {

    public static TodoItemProjection convertTo(TodoItemES source) {
        return TodoItemProjection.builder()
                .uuid(source.getUuid())
                .title(source.getTitle())
                .description(source.getDescription())
                .priority(source.getPriority())
                .build();
    }

}
