package com.example.todojava.service;

import com.example.todojava.model.Todo;
import com.example.todojava.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public List<Todo> getAll() {
        return repo.findAll();
    }

    public Todo create(Todo todo) {
        return repo.save(todo);
    }

    public Optional<Todo> getById(String id) {
        return repo.findById(id);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

}
