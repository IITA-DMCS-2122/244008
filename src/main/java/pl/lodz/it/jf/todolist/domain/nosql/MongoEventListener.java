package pl.lodz.it.jf.todolist.domain.nosql;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Service;
import pl.lodz.it.jf.todolist.domain.CreateTodoItemEventToEntityConverter;
import pl.lodz.it.jf.todolist.domain.TodoItemEntityToAnalyticsConverter;
import pl.lodz.it.jf.todolist.domain.TodoItemEntityToESConverter;
import pl.lodz.it.jf.todolist.domain.analytics.TodoItemAnalyticsJpaRepository;
import pl.lodz.it.jf.todolist.domain.elasticsearch.repository.TodoItemESRepository;
import pl.lodz.it.jf.todolist.domain.nosql.model.CreateTodoItemEvent;
import pl.lodz.it.jf.todolist.domain.sql.repository.TodoItemJpaRepository;

import java.time.LocalDateTime;

@AllArgsConstructor
@EnableMongoAuditing
@Service
public class MongoEventListener extends AbstractMongoEventListener<CreateTodoItemEvent> {

    private final TodoItemJpaRepository itemJpaRepository;

    private final TodoItemESRepository itemESRepository;

    private final TodoItemAnalyticsJpaRepository itemAnalyticsJpaRepository;

    private final SequenceGenerator sequenceGenerator;

    @Override
    public void onAfterSave(AfterSaveEvent<CreateTodoItemEvent> event) {
        var savedEntity = itemJpaRepository.saveAndFlush(CreateTodoItemEventToEntityConverter.convertTo(event.getSource()));
        itemESRepository.save(TodoItemEntityToESConverter.convertTo(savedEntity));
        itemAnalyticsJpaRepository.save(TodoItemEntityToAnalyticsConverter.convertTo(savedEntity));
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<CreateTodoItemEvent> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(CreateTodoItemEvent.SEQUENCE_NAME));
        }
    }

    @Override
    public void onBeforeSave(BeforeSaveEvent<CreateTodoItemEvent> event) {
        event.getSource().setCratedAt(LocalDateTime.now());
    }
}
