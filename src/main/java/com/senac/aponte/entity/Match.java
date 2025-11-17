package com.senac.aponte.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity

@Table(name = "`match`")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Integer id;

    @Column(name = "match_date")
    private LocalDate matchDate;

    @Column(name = "match_expires_at")
    private LocalDate expiresAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id") // <-- CORRIGIDO
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user_id1", referencedColumnName = "user_id") // <-- CORRIGIDO
    private User user2;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;

    @PrePersist
    protected void onCreate() {
        matchDate = LocalDate.now();
        expiresAt = LocalDate.now().plusDays(1); // <-- MUDANÇA (Expira ontem)
    }

    // --- GETTERS E SETTERS ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public LocalDate getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDate matchDate) { this.matchDate = matchDate; }
    public LocalDate getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDate expiresAt) { this.expiresAt = expiresAt; }
    public User getUser1() { return user1; }
    public void setUser1(User user1) { this.user1 = user1; }
    public User getUser2() { return user2; }
    public void setUser2(User user2) { this.user2 = user2; }
    public List<Message> getMessages() { return messages; }
    public void setMessages(List<Message> messages) { this.messages = messages; }

}