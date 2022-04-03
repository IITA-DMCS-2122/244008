package pl.lodz.it.jf.todolist.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.lodz.it.jf.todolist.composite.model.TodoItemSearchQuery;
import pl.lodz.it.jf.todolist.composite.model.TodoItemProjection;
import pl.lodz.it.jf.todolist.composite.service.TodoItemService;

import java.util.List;


@RestController
@RequestMapping("todoItems")
@AllArgsConstructor
public class TodoItemController {

    private final TodoItemService todoItemService;

    @PostMapping
    public void addTodoItem(@RequestBody TodoItemProjection todoItem) {
        todoItemService.create(todoItem);
    }

    @GetMapping("{uuid}")
    public TodoItemProjection getTodoItem(@PathVariable String uuid) {
        return todoItemService.getByUuid(uuid);
    }

    @PostMapping("search")
    public List<TodoItemProjection> search(@RequestBody TodoItemSearchQuery query) {
        return todoItemService.search(query);
    }

    @GetMapping
    public List<TodoItemProjection> getTodoItems() {
        return todoItemService.getAll();
    }

    @PutMapping
    public void editTodoItem(@RequestBody TodoItemProjection todoItem) {
        todoItemService.update(todoItem);
    }

    @DeleteMapping("{uuid}")
    public void deleteTodoItem(@PathVariable String uuid) {
        todoItemService.deleteByUuid(uuid);
    }

    @GetMapping("count")
    public long eventsCount() {
        return todoItemService.eventsCount();
    }

}
