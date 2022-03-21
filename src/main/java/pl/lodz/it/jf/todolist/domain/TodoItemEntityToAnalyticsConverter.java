package pl.lodz.it.jf.todolist.domain;

import pl.lodz.it.jf.todolist.domain.analytics.TodoItemAnalyticsEntity;
import pl.lodz.it.jf.todolist.domain.sql.model.TodoItemEntity;

public class TodoItemEntityToAnalyticsConverter {

    public static TodoItemAnalyticsEntity convertTo(TodoItemEntity source) {
        return TodoItemAnalyticsEntity.builder()
                .id(source.getId())
                .uuid(source.getUuid())
                .title(source.getTitle())
                .description(source.getDescription())
                .priority(source.getPriority())
                .build();
    }

    public static TodoItemEntity convertFrom(TodoItemAnalyticsEntity source) {
        return TodoItemEntity.builder()
                .id(source.getId())
                .uuid(source.getUuid())
                .title(source.getTitle())
                .description(source.getDescription())
                .priority(source.getPriority())
                .build();
    }

}
