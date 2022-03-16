package pl.lodz.it.jf.todolist.domain.elasticsearch.model;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Document(indexName = "todo_items")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemES {

    @Id
    private Integer id;

    private String uuid;

    private String title;

    private String description;

    private Integer priority;

}