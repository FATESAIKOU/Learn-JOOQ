package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository repository;

    public List<TodoItem> listTodos() {
        return repository.findAll();
    }

    public Optional<TodoItem> getTodo(Integer id) {
        return repository.findById(id);
    }

    public TodoItem createTodo(TodoItem item) {
        return repository.save(item);
    }

    public boolean updateTodo(Integer id, TodoItem item) {
        return repository.update(id, item) > 0;
    }

    public boolean deleteTodo(Integer id) {
        return repository.delete(id) > 0;
    }
}
