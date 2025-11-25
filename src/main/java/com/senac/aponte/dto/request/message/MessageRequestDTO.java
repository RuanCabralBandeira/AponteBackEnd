package com.senac.aponte.dto.request.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MessageRequestDTO {

    @NotBlank(message = "Text cannot be blank")
    private String text;

    @NotNull(message = "Sender ID cannot be blank")
    private Integer senderId;

    @NotNull(message = "Receiver ID cannot be blank")
    private Integer receiverId;


    // Getters e Setters manuais
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }
}
