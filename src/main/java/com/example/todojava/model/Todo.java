package com.example.todojava.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "todos")
public class Todo {
    @Id
    private String id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    private String description;
    private boolean completed;

    @NotBlank(message = "User ID is mandatory")
    private String userId;  // Reference to the User document's _id

    // Constructors
    public Todo() {
    }

    public Todo(String title, String description, boolean completed, String userId) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.userId = userId;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
