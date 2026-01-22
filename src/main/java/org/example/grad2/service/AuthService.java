package org.example.grad2.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.grad2.model.User;
import org.example.grad2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private static final String SECRET_KEY = "yourSecretKey";

    public boolean login(String email, String password) {
        try {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                return false;
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (user.getPassword().startsWith("$2a$")) {
                return encoder.matches(password, user.getPassword());
            } else {
                return user.getPassword().equals(password);
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean register(String firstName, String lastName, String email, String password,
                            String studentNumber, String faculty, String department) {
        if (userRepository.findByEmail(email) != null) {
            return false;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);

        return userRepository.save(firstName, lastName, email, hashedPassword,
                studentNumber, faculty, department);
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
