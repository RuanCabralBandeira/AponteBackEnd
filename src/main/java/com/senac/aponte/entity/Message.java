package com.senac.aponte.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer id;

    @Column(name = "message_text")
    private String text;

    @Column(name = "message_sent_at", updatable = false)
    private LocalDate sentAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id") // <-- CORRIGIDO
    private User sender;

    @ManyToOne
    @JoinColumn(name = "user_id1", referencedColumnName = "user_id") // <-- CORRIGIDO
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @PrePersist
    protected void onCreate() {
        sentAt = LocalDate.now();
    }

    // --- GETTERS E SETTERS ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public LocalDate getSentAt() { return sentAt; }
    public void setSentAt(LocalDate sentAt) { this.sentAt = sentAt; }
    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }
    public User getReceiver() { return receiver; }
    public void setReceiver(User receiver) { this.receiver = receiver; }
    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }

}