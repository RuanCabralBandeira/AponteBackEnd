package com.senac.aponte.dto.response.login;

public class LoginResponseDTO {
    private String token;
    private Integer userId; // <--- NOVO CAMPO


    public LoginResponseDTO(String token, Integer userId) {
        this.token = token;
        this.userId = userId;
    }

    // Getters e Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}