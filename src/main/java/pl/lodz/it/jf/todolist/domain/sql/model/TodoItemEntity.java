package pl.lodz.it.jf.todolist.domain.sql.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo_items")
public class TodoItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String uuid;

    private String title;

    private String description;

    private Integer priority;
}
