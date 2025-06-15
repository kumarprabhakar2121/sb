package com.example.todojava.service;

import com.example.todojava.exception.UserNotFoundException;
import com.example.todojava.model.Todo;
import com.example.todojava.repository.TodoRepository;
import com.example.todojava.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository repo;
    private final UserRepository userRepository;

    public TodoService(TodoRepository repo,  UserRepository userRepository) {
        this.repo = repo;
        this.userRepository = userRepository;
    }

    public List<Todo> getAll() {
        return repo.findAll();
    }

    public List<Todo> getByUserId(String userId) {
        return repo.findByUserId(userId);
    }

    public Todo create(Todo todo) throws BadRequestException {
        if (!userRepository.existsById(todo.getUserId())) {
            throw new UserNotFoundException("User does not exist for userId: " + todo.getUserId());
        }

        // TODO: Add notification after creating a todo item
        // TODO: Set a default due date for new TODOs

        return repo.save(todo);
    }

    public Optional<Todo> getById(String id) {
        return repo.findById(id);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    public Optional<Todo> update(String id, Todo updatedTodo) {
        return repo.findById(id).map(existing -> {
            existing.setTitle(updatedTodo.getTitle());
            existing.setDescription(updatedTodo.getDescription());
            existing.setCompleted(updatedTodo.isCompleted());
            return repo.save(existing);
        });
    }
}