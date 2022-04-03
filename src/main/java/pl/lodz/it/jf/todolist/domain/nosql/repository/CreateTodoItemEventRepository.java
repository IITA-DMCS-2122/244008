package pl.lodz.it.jf.todolist.domain.nosql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lodz.it.jf.todolist.domain.nosql.model.CreateTodoItemEvent;

public interface CreateTodoItemEventRepository extends MongoRepository<CreateTodoItemEvent, Integer> {
}
