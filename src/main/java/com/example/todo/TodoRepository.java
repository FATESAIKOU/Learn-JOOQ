package com.example.todo;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.jooq.impl.DSL.*;

import com.example.todo.jooq.tables.TodoItems;
import com.example.todo.jooq.tables.records.TodoItemsRecord;

@Repository
public class TodoRepository {
    @Autowired
    private DSLContext dsl;

    public List<TodoItem> findAll() {
        Result<TodoItemsRecord> result = dsl.selectFrom(TodoItems.TODO_ITEMS).fetch();
        return result.map(this::toTodoItem);
    }

    public Optional<TodoItem> findById(Integer id) {
        TodoItemsRecord record = dsl.selectFrom(TodoItems.TODO_ITEMS)
                .where(TodoItems.TODO_ITEMS.ID.eq(id))
                .fetchOne();
        return Optional.ofNullable(record).map(this::toTodoItem);
    }

    public TodoItem save(TodoItem item) {
        TodoItemsRecord record = dsl.insertInto(TodoItems.TODO_ITEMS)
                .set(TodoItems.TODO_ITEMS.TITLE, item.getTitle())
                .set(TodoItems.TODO_ITEMS.DESCRIPTION, item.getDescription())
                .set(TodoItems.TODO_ITEMS.COMPLETED, item.getCompleted())
                .returning()
                .fetchOne();
        return toTodoItem(record);
    }

    public int update(Integer id, TodoItem item) {
        return dsl.update(TodoItems.TODO_ITEMS)
                .set(TodoItems.TODO_ITEMS.TITLE, item.getTitle())
                .set(TodoItems.TODO_ITEMS.DESCRIPTION, item.getDescription())
                .set(TodoItems.TODO_ITEMS.COMPLETED, item.getCompleted())
                .set(TodoItems.TODO_ITEMS.UPDATED_AT, currentTimestamp().cast(LocalDateTime.class))
                .where(TodoItems.TODO_ITEMS.ID.eq(id))
                .execute();
    }

    public int delete(Integer id) {
        return dsl.deleteFrom(TodoItems.TODO_ITEMS)
                .where(TodoItems.TODO_ITEMS.ID.eq(id))
                .execute();
    }

    private TodoItem toTodoItem(Record record) {
        if (record == null) return null;
        TodoItem item = new TodoItem();
        item.setId(record.get(TodoItems.TODO_ITEMS.ID));
        item.setTitle(record.get(TodoItems.TODO_ITEMS.TITLE));
        item.setDescription(record.get(TodoItems.TODO_ITEMS.DESCRIPTION));
        item.setCompleted(record.get(TodoItems.TODO_ITEMS.COMPLETED));
        item.setCreatedAt(record.get(TodoItems.TODO_ITEMS.CREATED_AT));
        item.setUpdatedAt(record.get(TodoItems.TODO_ITEMS.UPDATED_AT));
        return item;
    }
}
