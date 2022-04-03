package pl.lodz.it.jf.todolist.domain.nosql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("todoitem_create_event")
public class CreateTodoItemEvent {

    @Transient
    public static final String SEQUENCE_NAME = "create_item_event_sequence";

    @Id
    private long id;

    private TodoItemDetails details;

    private LocalDateTime cratedAt;

}