package com.example.todojava.controller;

import com.example.todojava.model.Todo;
import com.example.todojava.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Todo> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        return service.create(todo);
    }

    @GetMapping("/{id}")
    public Todo get(@PathVariable String id) {
        return service.getById(id).orElse(null);
    }

//    @PutMapping("/{id}")
//    public Todo update(@PathVariable String id, @RequestBody Todo todo) {
//        return service.update(id, todo);
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
