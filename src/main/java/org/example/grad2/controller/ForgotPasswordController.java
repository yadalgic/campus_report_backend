package org.example.grad2.controller;

import org.example.grad2.model.User;
import org.example.grad2.repository.UserRepository;
import org.example.grad2.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@CrossOrigin(origins = "*")
public class ForgotPasswordController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return "Email not found!";
        }

        String newPassword = generateRandomPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(newPassword);
        boolean success = userRepository.updatePasswordByEmail(email, encodedPassword);

        if (success) {
            emailService.sendEmail(
                    email,
                    "CampusReport - Password Reset",
                    "Your new password is: " + newPassword + "\n\nPlease change it after logging in."
            );
            return "New password sent to your email.";
        } else {
            return "Password reset failed.";
        }
    }

    private String generateRandomPassword() {
        return String.valueOf((int)(Math.random() * 900000) + 100000);
    }
}
