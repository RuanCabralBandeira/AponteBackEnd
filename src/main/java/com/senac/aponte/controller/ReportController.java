package com.senac.aponte.controller;

import com.senac.aponte.dto.request.report.ReportRequestDTO;
import com.senac.aponte.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "Denúncias", description = "Endpoint para criar denúncias")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    @Operation(summary = "Criar uma nova denúncia")
    public ResponseEntity<Void> createReport(@Valid @RequestBody ReportRequestDTO reportRequest) {
        reportService.createReport(reportRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}