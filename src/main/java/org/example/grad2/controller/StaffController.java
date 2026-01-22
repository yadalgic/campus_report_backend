package org.example.grad2.controller;

import org.example.grad2.model.Report;
import org.example.grad2.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    private ReportRepository reportRepository;

    @PutMapping("/report/status")
    public boolean updateStatus(@RequestParam Long id, @RequestParam String status) {
        return reportRepository.updateStatus(id, status);
    }

    @PutMapping("/report/category")
    public boolean updateCategory(@RequestParam Long id, @RequestParam String category) {
        return reportRepository.updateCategory(id, category);
    }
}
