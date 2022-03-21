package pl.lodz.it.jf.todolist.domain.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.it.jf.todolist.domain.sql.model.TodoItemEntity;

@Transactional("entityManagerFactory")
public interface TodoItemJpaRepository extends JpaRepository<TodoItemEntity, Integer> {

    TodoItemEntity findByUuid(String uuid);

    void deleteByUuid(String uuid);

}
