package org.example.grad2.service;

import org.example.grad2.model.Report;
import org.example.grad2.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public boolean saveReport(Report report) {
        return reportRepository.save(report);
    }
    public boolean deleteReportById(int id) {
        return reportRepository.deleteById(id);
    }


}
