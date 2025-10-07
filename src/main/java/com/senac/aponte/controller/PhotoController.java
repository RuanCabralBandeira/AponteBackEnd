package com.senac.aponte.controller;

import com.senac.aponte.dto.request.photo.PhotoRequestDTO;
import com.senac.aponte.dto.response.photo.PhotoResponseDTO;
import com.senac.aponte.service.PhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
@Tag(name="Fotos", description = "Endpoints para gerenciamento de fotos de perfis")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/profile/{profileId}")
    @Operation(summary = "Adicionar uma nova foto a um perfil")
    public ResponseEntity<PhotoResponseDTO> addPhoto(@PathVariable Integer profileId, @Valid @RequestBody PhotoRequestDTO photoRequest) {
        PhotoResponseDTO newPhoto = photoService.addPhotoToProfile(profileId, photoRequest);
        return new ResponseEntity<>(newPhoto, HttpStatus.CREATED);
    }

    @GetMapping("/profile/{profileId}")
    @Operation(summary = "Listar todas as fotos de um perfil")
    public ResponseEntity<List<PhotoResponseDTO>> getPhotos(@PathVariable Integer profileId) {
        List<PhotoResponseDTO> photos = photoService.getPhotosByProfileId(profileId);
        return ResponseEntity.ok(photos);
    }

    @DeleteMapping("/{photoId}")
    @Operation(summary = "Deletar uma foto pelo seu ID")
    public ResponseEntity<Void> deletePhoto(@PathVariable Integer photoId) {
        photoService.deletePhoto(photoId);
        return ResponseEntity.noContent().build();
    }
}
