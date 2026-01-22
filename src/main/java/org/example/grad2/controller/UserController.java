package org.example.grad2.controller;

import org.example.grad2.model.User;
import org.example.grad2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public User getUserByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }

    @GetMapping("/users/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/delete-user")
    public boolean deleteUser(@RequestParam String email) {
        return userRepository.deleteByEmail(email);
    }

    @PostMapping("/block-user")
    public boolean blockUser(@RequestParam String email, @RequestParam int hours) {
        LocalDateTime until = LocalDateTime.now().plusHours(hours);
        return userRepository.blockUntil(email, until);
    }

    @PostMapping("/unblock-user")
    public boolean unblockUser(@RequestParam String email) {
        return userRepository.blockUntil(email, null);
    }

}
