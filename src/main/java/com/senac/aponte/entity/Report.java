package com.senac.aponte.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Integer id;

    @Column(name = "report_reason")
    private String reason;

    @Column(name = "report_reported_at")
    private LocalDateTime reportedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id") // <-- CORRIGIDO
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "user_id1", referencedColumnName = "user_id") // <-- CORRIGIDO
    private User reported;

    @PrePersist
    protected void onCreate() {
        reportedAt = LocalDateTime.now();
    }

    // --- GETTERS E SETTERS ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public LocalDateTime getReportedAt() { return reportedAt; }
    public void setReportedAt(LocalDateTime reportedAt) { this.reportedAt = reportedAt; }
    public User getReporter() { return reporter; }
    public void setReporter(User reporter) { this.reporter = reporter; }
    public User getReported() { return reported; }
    public void setReported(User reported) { this.reported = reported; }

}