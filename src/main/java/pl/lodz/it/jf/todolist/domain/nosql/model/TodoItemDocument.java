package pl.lodz.it.jf.todolist.domain.nosql.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Document(collection = "todo_items")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemDocument {

    @Id
    private Integer id;

    private String uuid;

    private String title;

    private String description;

    private Integer priority;
}
