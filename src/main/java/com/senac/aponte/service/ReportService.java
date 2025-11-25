package com.senac.aponte.service;

import com.senac.aponte.dto.request.report.ReportRequestDTO;
import com.senac.aponte.entity.Report;
import com.senac.aponte.entity.User;
import com.senac.aponte.repository.ReportRepository;
import com.senac.aponte.repository.UserRepository;
import org.springframework.stereotype.Service;
@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    public ReportService(ReportRepository reportRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    public void createReport(ReportRequestDTO reportRequest) {
        User reporter = userRepository.findById(reportRequest.getReporterId())
                .orElseThrow(() -> new RuntimeException("Reporter user not found"));
        User reported = userRepository.findById(reportRequest.getReportedId())
                .orElseThrow(() -> new RuntimeException("Reported user not found"));
        Report report = new Report();
        report.setReason(reportRequest.getReason());
        report.setReporter(reporter);
        report.setReported(reported);
        reportRepository.save(report);
    }

}