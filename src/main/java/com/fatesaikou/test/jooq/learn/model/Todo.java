package com.fatesaikou.test.jooq.learn.model;

import lombok.Data;

@Data
public class Todo {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}