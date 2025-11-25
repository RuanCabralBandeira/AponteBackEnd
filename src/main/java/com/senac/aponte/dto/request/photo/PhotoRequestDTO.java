package com.senac.aponte.dto.request.photo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PhotoRequestDTO {

    @NotBlank(message = "URL cannot be blank")
    private String url;

    @NotNull(message = "Order index cannot be null")
    private Integer orderIndex;

    // Getters e Setters manuais
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }
}
