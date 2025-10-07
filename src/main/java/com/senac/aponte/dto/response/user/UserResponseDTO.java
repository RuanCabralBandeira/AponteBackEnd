package com.senac.aponte.dto.response.user;

import java.time.LocalDateTime;

public class UserResponseDTO {
    private Integer id;
    private String email;
    private Integer status;
    private LocalDateTime createdAt;

    // Getters e Setters manuais
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}