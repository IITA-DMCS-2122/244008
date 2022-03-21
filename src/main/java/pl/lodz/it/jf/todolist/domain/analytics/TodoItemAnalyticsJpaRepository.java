package pl.lodz.it.jf.todolist.domain.analytics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional("db1TransactionManager")
public interface TodoItemAnalyticsJpaRepository extends JpaRepository<TodoItemAnalyticsEntity, Integer> {

}