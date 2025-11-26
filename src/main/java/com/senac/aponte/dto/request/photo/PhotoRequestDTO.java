package com.senac.aponte.dto.request.photo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PhotoRequestDTO {

    @NotBlank(message = "data cannot be blank")
    private String data;
    private String url;

    @NotNull(message = "Order index cannot be null")
    private Integer orderIndex;

    // Getters e Setters manuais
    public String getUrl() {
        return url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
