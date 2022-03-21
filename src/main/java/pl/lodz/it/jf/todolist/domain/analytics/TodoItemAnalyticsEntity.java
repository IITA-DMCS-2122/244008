package pl.lodz.it.jf.todolist.domain.analytics;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo_items")
public class TodoItemAnalyticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String uuid;

    private String title;

    private String description;

    private Integer priority;
}
