package pl.lodz.it.jf.todolist.domain.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.lodz.it.jf.todolist.domain.elasticsearch.model.TodoItemES;

import java.util.List;

public interface TodoItemESRepository extends ElasticsearchRepository<TodoItemES, Integer> {

    List<TodoItemES> findByDescriptionContains(String desc);

    TodoItemES findByUuid(String uuid);

    void deleteByUuid(String uuid);
}
