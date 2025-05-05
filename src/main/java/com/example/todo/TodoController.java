package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService service;

    @GetMapping
    public List<TodoItem> listTodos() {
        return service.listTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodo(@PathVariable Integer id) {
        return service.getTodo(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TodoItem createTodo(@RequestBody TodoItem item) {
        return service.createTodo(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTodo(@PathVariable Integer id, @RequestBody TodoItem item) {
        boolean updated = service.updateTodo(id, item);
        return updated ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id) {
        boolean deleted = service.deleteTodo(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
