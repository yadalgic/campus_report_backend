package org.example.grad2.repository;

import org.example.grad2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) -> {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setStudentNumber(rs.getString("student_number"));
                user.setFaculty(rs.getString("faculty"));
                user.setDepartment(rs.getString("department"));
                user.setRole(rs.getString("role"));
                Timestamp blocked = rs.getTimestamp("blocked_until");
                user.setBlockedUntil(blocked != null ? blocked.toLocalDateTime() : null);
                return user;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean save(String firstName, String lastName, String email, String password,
                        String studentNumber, String faculty, String department) {
        String sql = "INSERT INTO users (first_name, last_name, email, password, student_number, faculty, department) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, firstName, lastName, email, password, studentNumber, faculty, department);
        return result > 0;
    }

    public boolean addUser(User user) {
        String sql = "INSERT INTO users (first_name, last_name, email, password, student_number, faculty, department, role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getStudentNumber(),
                user.getFaculty(),
                user.getDepartment(),
                user.getRole()) > 0;
    }

    public boolean updatePasswordByEmail(String email, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        return jdbcTemplate.update(sql, newPassword, email) > 0;
    }

    public boolean deleteByEmail(String email) {
        String sql = "DELETE FROM users WHERE email = ?";
        return jdbcTemplate.update(sql, email) > 0;
    }

    public boolean blockUntil(String email, LocalDateTime until) {
        String sql = "UPDATE users SET blocked_until = ? WHERE email = ?";
        Timestamp timestamp = until != null ? Timestamp.valueOf(until) : null;
        return jdbcTemplate.update(sql, timestamp, email) > 0;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setStudentNumber(rs.getString("student_number"));
            user.setFaculty(rs.getString("faculty"));
            user.setDepartment(rs.getString("department"));
            user.setRole(rs.getString("role"));
            Timestamp blocked = rs.getTimestamp("blocked_until");
            user.setBlockedUntil(blocked != null ? blocked.toLocalDateTime() : null);
            return user;
        });
    }
}
