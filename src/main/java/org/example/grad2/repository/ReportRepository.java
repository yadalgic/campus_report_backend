package org.example.grad2.repository;

import org.example.grad2.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.example.grad2.service.ReportService;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    public boolean save(Report report) {
        String sql = "INSERT INTO reports (category, description, image_url, latitude, longitude, status, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql,
                report.getCategory(),
                report.getDescription(),
                report.getImageUrl(),
                report.getLatitude(),
                report.getLongitude(),
                report.getStatus(),
                report.getEmail()

        );
        return result > 0;
    }

    public List<Report> findByEmail(String email) {
        String sql = "SELECT * FROM reports WHERE email = ?";
        return jdbcTemplate.query(sql, new Object[]{email}, (rs, rowNum) -> {
            Report report = new Report();
            report.setId(rs.getLong("id"));
            report.setCategory(rs.getString("category"));
            report.setDescription(rs.getString("description"));
            report.setLatitude(rs.getDouble("latitude"));
            report.setLongitude(rs.getDouble("longitude"));
            report.setImageUrl(rs.getString("image_url"));
            report.setStatus(rs.getString("status"));
            report.setEmail(rs.getString("email"));
            report.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            return report;
        });
    }

    public List<Report> findAll() {
        String sql = "SELECT * FROM reports ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Report report = new Report();
            report.setId(rs.getLong("id"));
            report.setCategory(rs.getString("category"));
            report.setDescription(rs.getString("description"));
            report.setLatitude(rs.getDouble("latitude"));
            report.setLongitude(rs.getDouble("longitude"));
            report.setImageUrl(rs.getString("image_url"));
            report.setEmail(rs.getString("email"));
            report.setStatus(rs.getString("status"));
            report.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            return report;
        });
    }

    public boolean updateStatus(Long id, String status) {
        String sql = "UPDATE reports SET status = ? WHERE id = ?";
        return jdbcTemplate.update(sql, status, id) > 0;
    }

    public boolean updateCategory(Long id, String category) {
        String sql = "UPDATE reports SET category = ? WHERE id = ?";
        return jdbcTemplate.update(sql, category, id) > 0;
    }

    public boolean deleteById(int id) {
        String sql = "DELETE FROM reports WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Report> findByDepartment(String department) {
        String sql = "SELECT * FROM reports WHERE department = ?";
        return jdbcTemplate.query(sql, new Object[]{department}, (rs, rowNum) -> {
            Report report = new Report();
            report.setId(rs.getLong("id"));
            report.setCategory(rs.getString("category"));
            report.setDescription(rs.getString("description"));
            report.setLatitude(rs.getDouble("latitude"));
            report.setLongitude(rs.getDouble("longitude"));
            report.setImageUrl(rs.getString("image_url"));
            report.setEmail(rs.getString("email"));
            report.setStatus(rs.getString("status"));
            report.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            return report;
        });
    }





}
