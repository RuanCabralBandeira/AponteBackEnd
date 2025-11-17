package com.senac.aponte.dto.response.profile;

import com.senac.aponte.dto.response.photo.PhotoResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ProfileResponseDTO {
    private Integer id;
    private String name;
    private LocalDate birthDate;
    private String bio;
    private String lastLocation;
    private LocalDateTime updatedAt;
    private Integer userId;

    private List<PhotoResponseDTO> photos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<PhotoResponseDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoResponseDTO> photos) {
        this.photos = photos;
    }
}