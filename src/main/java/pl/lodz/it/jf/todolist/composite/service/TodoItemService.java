package pl.lodz.it.jf.todolist.composite.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.it.jf.todolist.composite.converter.TodoItemESToProjectionConverter;
import pl.lodz.it.jf.todolist.composite.model.TodoItemProjection;
import pl.lodz.it.jf.todolist.composite.model.TodoItemSearchQuery;
import pl.lodz.it.jf.todolist.domain.TodoItemEntityToESConverter;
import pl.lodz.it.jf.todolist.domain.elasticsearch.model.TodoItemES;
import pl.lodz.it.jf.todolist.domain.elasticsearch.repository.TodoItemESRepository;
import pl.lodz.it.jf.todolist.domain.nosql.model.CreateTodoItemEvent;
import pl.lodz.it.jf.todolist.domain.nosql.model.TodoItemDetails;
import pl.lodz.it.jf.todolist.domain.nosql.repository.CreateTodoItemEventRepository;
import pl.lodz.it.jf.todolist.domain.sql.model.TodoItemEntity;
import pl.lodz.it.jf.todolist.domain.sql.repository.TodoItemJpaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class TodoItemService {

    private final CreateTodoItemEventRepository createTodoItemEventRepository;

    private final TodoItemJpaRepository itemJpaRepository;

    private final TodoItemESRepository itemESRepository;

    public TodoItemProjection getByUuid(String uuid) {
        TodoItemES todoItemES = itemESRepository.findByUuid(uuid);
        return TodoItemESToProjectionConverter.convertTo(todoItemES);
    }

    public List<TodoItemProjection> getAll() {
        Iterable<TodoItemES> todoItems = itemESRepository.findAll();
        return StreamSupport.stream(todoItems.spliterator(), false)
                .map(TodoItemESToProjectionConverter::convertTo)
                .collect(Collectors.toList());
    }

    public void deleteByUuid(String uuid) {
        itemJpaRepository.deleteByUuid(uuid);
        itemESRepository.deleteByUuid(uuid);
    }

    public void create(TodoItemProjection projection) {
        CreateTodoItemEvent event = CreateTodoItemEvent.builder()
                .details(TodoItemDetails.builder()
                        .title(projection.getTitle())
                        .uuid(UUID.randomUUID().toString())
                        .priority(projection.getPriority())
                        .description(projection.getDescription())
                        .build())
                .build();

        createTodoItemEventRepository.save(event);
    }

    public void update(TodoItemProjection projection) {
        TodoItemEntity itemEntity = itemJpaRepository.findByUuid(projection.getUuid());
        itemEntity.setTitle(projection.getTitle());
        itemEntity.setDescription(projection.getDescription());
        itemEntity.setPriority(projection.getPriority());
        itemJpaRepository.save(itemEntity);
        itemESRepository.save(TodoItemEntityToESConverter.convertTo(itemEntity));
    }

    public List<TodoItemProjection> search(TodoItemSearchQuery query) {
        var todoItems = itemESRepository.findByDescriptionContains(query.getDescription());
        return todoItems.stream().map(TodoItemESToProjectionConverter::convertTo).collect(Collectors.toList());
    }

    public long eventsCount() {
        return createTodoItemEventRepository.count();
    }

}
