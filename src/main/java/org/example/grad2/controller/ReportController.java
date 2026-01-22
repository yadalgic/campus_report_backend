package org.example.grad2.controller;

import org.example.grad2.model.Report;
import org.example.grad2.repository.ReportRepository;
import org.example.grad2.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ReportService reportService;

    @PostMapping
    public boolean saveReport(@RequestBody Report report) {
        report.setStatus("Submitted");
        return reportRepository.save(report);
    }

    @GetMapping("/by-email")
    public List<Report> getReportsByEmail(@RequestParam String email) {
        return reportRepository.findByEmail(email);
    }
    @GetMapping
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @PutMapping("/report/status")
    public boolean updateStatus(@RequestParam Long id, @RequestParam String status) {
        return reportRepository.updateStatus(id, status);
    }
    @DeleteMapping("/deleteReport")
    public ResponseEntity<String> deleteReport(@RequestParam int id) {
        boolean success = reportService.deleteReportById(id);
        if (success) {
            return ResponseEntity.ok("Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
    }

    @GetMapping("/by-department")
    public List<Report> getReportsByDepartment(@RequestParam String department) {
        return reportRepository.findByDepartment(department);
    }


}
