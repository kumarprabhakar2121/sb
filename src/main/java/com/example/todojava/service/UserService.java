package com.example.todojava.service;

import com.example.todojava.model.User;
import com.example.todojava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository repo;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserService(UserRepository repo, MongoTemplate mongoTemplate) {
        this.repo = repo;
        this.mongoTemplate = mongoTemplate;
    }

    public List<User> getAll() {
        return repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public User create(User user) {
        return repo.save(user);
    }

    public Optional<User> getById(String id) {
        return repo.findById(id);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    public Map<String, Object> getPaginatedUsers(int page, int limit) {
        int skip = (page - 1) * limit;

        Aggregation agg = Aggregation.newAggregation(
                Aggregation.sort(Sort.Direction.DESC, "id"),
                Aggregation.skip((long) skip),
                Aggregation.limit(limit)
        );

        List<User> users = mongoTemplate.aggregate(agg, "users", User.class).getMappedResults();

        long total = mongoTemplate.count(new Query(), User.class);

        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("page", page);
        result.put("limit", limit);
        result.put("users", users);
        return result;
    }
}
