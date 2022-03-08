package pl.lodz.it.jf.todolist.domain.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.it.jf.todolist.domain.sql.model.TodoItemEntity;

import javax.transaction.Transactional;

@Transactional
public interface TodoItemJpaRepository extends JpaRepository<TodoItemEntity, Integer> {

    TodoItemEntity findByUuid(String uuid);

    void deleteByUuid(String uuid);

}
