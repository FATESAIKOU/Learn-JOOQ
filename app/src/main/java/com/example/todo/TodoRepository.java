package com.example.todo;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.*;

@Repository
public class TodoRepository {
    @Autowired
    private DSLContext dsl;

    public List<TodoItem> findAll() {
        Result<Record> result = dsl.select().from(table("todo_items")).fetch();
        return result.map(this::toTodoItem);
    }

    public Optional<TodoItem> findById(Integer id) {
        Record record = dsl.select().from(table("todo_items")).where(field("id").eq(id)).fetchOne();
        return Optional.ofNullable(record).map(this::toTodoItem);
    }

    public TodoItem save(TodoItem item) {
        Record record = dsl.insertInto(table("todo_items"))
                .set(field("title"), item.getTitle())
                .set(field("description"), item.getDescription())
                .set(field("completed"), item.getCompleted())
                .returning()
                .fetchOne();
        return toTodoItem(record);
    }

    public int update(Integer id, TodoItem item) {
        return dsl.update(table("todo_items"))
                .set(field("title"), item.getTitle())
                .set(field("description"), item.getDescription())
                .set(field("completed"), item.getCompleted())
                .set(field("updated_at"), currentTimestamp())
                .where(field("id").eq(id))
                .execute();
    }

    public int delete(Integer id) {
        return dsl.deleteFrom(table("todo_items")).where(field("id").eq(id)).execute();
    }

    private TodoItem toTodoItem(Record record) {
        if (record == null) return null;
        TodoItem item = new TodoItem();
        item.setId(record.get("id", Integer.class));
        item.setTitle(record.get("title", String.class));
        item.setDescription(record.get("description", String.class));
        item.setCompleted(record.get("completed", Boolean.class));
        item.setCreatedAt(record.get("created_at", java.time.LocalDateTime.class));
        item.setUpdatedAt(record.get("updated_at", java.time.LocalDateTime.class));
        return item;
    }
}
