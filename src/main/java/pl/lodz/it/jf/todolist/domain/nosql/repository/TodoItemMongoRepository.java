package pl.lodz.it.jf.todolist.domain.nosql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lodz.it.jf.todolist.domain.nosql.model.TodoItemDocument;

import javax.transaction.Transactional;

@Transactional
public interface TodoItemMongoRepository extends MongoRepository<TodoItemDocument, Integer> {

    TodoItemDocument findByUuid(String uuid);

    void deleteByUuid(String uuid);

}
