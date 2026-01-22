/*package org.example.grad2.controller;


import org.example.grad2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {

    @Autowired
    private UserRepository userRepository;

    public boolean resetPassword(String email, String newPassword) {
        if (!userRepository.emailExists(email)) {
            return false;
        }
        userRepository.updatePassword(email, newPassword);
        return true;
    }
}
*/