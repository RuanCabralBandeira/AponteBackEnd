package com.senac.aponte.controller;

import com.senac.aponte.entity.Photo;
import com.senac.aponte.entity.Profile;
import com.senac.aponte.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;


    @GetMapping("/profile/{profileId}")
    public ResponseEntity<byte[]> getPhotoByProfile(@PathVariable Integer profileId) {

        Optional<Photo> photoOptional = photoRepository.findFirstByProfileId(profileId);

        if (photoOptional.isPresent()) {
            Photo photo = photoOptional.get();


            MediaType mediaType = MediaType.IMAGE_JPEG;
            if (photo.getUrl() != null && photo.getUrl().toLowerCase().endsWith(".png")) {
                mediaType = MediaType.IMAGE_PNG;
            }

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(photo.getPhotoData());
        } else {

            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("profileId") Integer profileId) {

        try {

            Optional<Photo> oldPhoto = photoRepository.findFirstByProfileId(profileId);
            if (oldPhoto.isPresent()) {
                photoRepository.delete(oldPhoto.get());
            }

            Photo photo = new Photo();
            photo.setPhotoData(file.getBytes());
            photo.setUrl(file.getOriginalFilename());
            photo.setUploadedAt(LocalDateTime.now());

            Profile profile = new Profile();
            profile.setId(profileId);

            photo.setProfile(profile);

            photoRepository.save(photo);

            return ResponseEntity.ok("Sucesso! Imagem salva com ID: " + photo.getId());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro: " + e.getMessage());
        }
    }
}