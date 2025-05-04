package com.fatesaikou.test.jooq.learn.service;

import com.fatesaikou.test.jooq.learn.mapper.TodoMapper;
import com.fatesaikou.test.jooq.learn.model.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoMapper todoMapper;

    public TodoService(TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    public Todo findById(Long id) {
        return todoMapper.findById(id);
    }

    public List<Todo> findAll() {
        return todoMapper.findAll();
    }

    public void create(Todo todo) {
        todoMapper.insert(todo);
    }

    public void update(Todo todo) {
        todoMapper.update(todo);
    }

    public void delete(Long id) {
        todoMapper.delete(id);
    }
}