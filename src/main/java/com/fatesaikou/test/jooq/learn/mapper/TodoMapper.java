package com.fatesaikou.test.jooq.learn.mapper;

import com.fatesaikou.test.jooq.learn.model.Todo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TodoMapper {

    @Select("SELECT * FROM todos WHERE id = #{id}")
    Todo findById(Long id);

    @Select("SELECT * FROM todos")
    List<Todo> findAll();

    @Insert("INSERT INTO todos (title, description, completed) VALUES (#{title}, #{description}, #{completed})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Todo todo);

    @Update("UPDATE todos SET title = #{title}, description = #{description}, completed = #{completed} WHERE id = #{id}")
    void update(Todo todo);

    @Delete("DELETE FROM todos WHERE id = #{id}")
    void delete(Long id);
}