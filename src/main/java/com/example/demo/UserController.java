package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repo;

    @PostMapping
    public User save(@RequestBody User user) {
        return repo.save(user);
    }

    @GetMapping
    public List<User> getAll() {
        return repo.findAll();
    }
}

// Repository inside same file (simple way)
interface UserRepository extends MongoRepository<User, String> {}