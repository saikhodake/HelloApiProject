package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repo;

    // CREATE USER
    @PostMapping
    public User save(@RequestBody User user) {
        return repo.save(user);
    }

    // GET ALL USERS
    @GetMapping
    public List<User> getAll() {
        return repo.findAll();
    }

    // LOGIN USER
    @PostMapping("/login")
    public User login(@RequestBody User input) {
        if (input.getUsername() == null || input.getPassword() == null) {
            // optional: return null or throw exception if input is invalid
            return null;
        }

        // Use repository method to query MongoDB safely
        Optional<User> userOpt = repo.findByUsernameAndPassword(input.getUsername(), input.getPassword());

        return userOpt.orElse(null); // returns user if found, else null
    }

}

// INLINE REPOSITORY
interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsernameAndPassword(String username, String password);
}