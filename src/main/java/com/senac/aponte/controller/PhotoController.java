package com.senac.aponte.controller;

import com.senac.aponte.entity.Photo;
import com.senac.aponte.entity.Profile;
import com.senac.aponte.repository.PhotoRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("profileId") Integer profileId) { // Recebe o 7 do Postman

        try {
            Photo photo = new Photo();
            photo.setPhotoData(file.getBytes());
            photo.setUrl(file.getOriginalFilename());
            photo.setUploadedAt(LocalDateTime.now());


            Profile profile = new Profile();
            profile.setId(profileId);

            photo.setProfile(profile);
            // ------------------------

            photoRepository.save(photo);

            return ResponseEntity.ok("Sucesso! Imagem salva com ID: " + photo.getId());

        } catch (Exception e) {
            e.printStackTrace(); // Isso ajuda a ver o erro no console
            return ResponseEntity.internalServerError().body("Erro: " + e.getMessage());
        }
    }
}

