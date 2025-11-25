package com.senac.aponte.dto.request.report;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReportRequestDTO {

    @NotNull(message = "Reporter ID cannot be null")
    private Integer reporterId;

    @NotNull(message = "Reported ID cannot be null")
    private Integer reportedId;

    @NotBlank(message = "Reason cannot be blank")
    private String reason;

    // Getters e Setters manuais
    public Integer getReporterId() {
        return reporterId;
    }

    public void setReporterId(Integer reporterId) {
        this.reporterId = reporterId;
    }

    public Integer getReportedId() {
        return reportedId;
    }

    public void setReportedId(Integer reportedId) {
        this.reportedId = reportedId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
