package org.example.grad2.controller;

import org.example.grad2.model.User;
import org.example.grad2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add-user")
    public boolean addUser(@RequestBody User user) {
        return userRepository.addUser(user);
    }

    @DeleteMapping("/delete-user")
    public boolean deleteUser(@RequestParam String email) {
        return userRepository.deleteByEmail(email);
    }

    // ✅ Süreli engelleme
    @PostMapping("/block-user")
    public boolean blockUser(@RequestParam String email, @RequestParam int hours) {
        LocalDateTime until = LocalDateTime.now().plusHours(hours);
        return userRepository.blockUntil(email, until);
    }

    // ✅ Engel kaldırma
    @PostMapping("/unblock-user")
    public boolean unblockUser(@RequestParam String email) {
        return userRepository.blockUntil(email, null);
    }
}
