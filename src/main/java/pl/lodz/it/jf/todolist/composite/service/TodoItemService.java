package pl.lodz.it.jf.todolist.composite.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.it.jf.todolist.composite.converter.TodoItemDocumentToProjectionConverter;
import pl.lodz.it.jf.todolist.composite.converter.TodoItemEntityToProjectionConverter;
import pl.lodz.it.jf.todolist.composite.model.TodoItemProjection;
import pl.lodz.it.jf.todolist.domain.TodoItemEntityToDocumentConverter;
import pl.lodz.it.jf.todolist.domain.nosql.model.TodoItemDocument;
import pl.lodz.it.jf.todolist.domain.nosql.repository.TodoItemMongoRepository;
import pl.lodz.it.jf.todolist.domain.sql.model.TodoItemEntity;
import pl.lodz.it.jf.todolist.domain.sql.repository.TodoItemJpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class TodoItemService {

    private final TodoItemMongoRepository itemMongoRepository;

    private final TodoItemJpaRepository itemJpaRepository;

    public TodoItemProjection getByUuid(String uuid) {
        TodoItemDocument todoItemDocument = itemMongoRepository.findByUuid(uuid);
        return TodoItemDocumentToProjectionConverter.convertTo(todoItemDocument);
    }

    public List<TodoItemProjection> getAll() {
        List<TodoItemDocument> todoItemDocuments = itemMongoRepository.findAll();
        return todoItemDocuments.stream()
                .map(TodoItemDocumentToProjectionConverter::convertTo)
                .collect(Collectors.toList());
    }

    public void deleteByUuid(String uuid) {
        itemJpaRepository.deleteByUuid(uuid);
        itemMongoRepository.deleteByUuid(uuid);
    }

    public void create(TodoItemProjection projection) {
        projection.setUuid(UUID.randomUUID().toString());
        var savedEntity = itemJpaRepository.saveAndFlush(TodoItemEntityToProjectionConverter.convertFrom(projection));
        itemMongoRepository.save(TodoItemEntityToDocumentConverter.convertTo(savedEntity));
    }

    public void update(TodoItemProjection projection) {
        TodoItemEntity itemEntity = itemJpaRepository.findByUuid(projection.getUuid());
        itemEntity.setTitle(projection.getTitle());
        itemEntity.setDescription(projection.getDescription());
        itemEntity.setPriority(projection.getPriority());
        itemEntity.setCreationDate(projection.getCreationDate());
        itemJpaRepository.save(itemEntity);
        itemMongoRepository.save(TodoItemEntityToDocumentConverter.convertTo(itemEntity));
    }

}
