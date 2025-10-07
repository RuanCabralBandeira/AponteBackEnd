package com.senac.aponte.controller;

import com.senac.aponte.dto.request.profile.ProfileRequestDTO;
import com.senac.aponte.dto.response.profile.ProfileResponseDTO;
import com.senac.aponte.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@Tag(name="Perfis", description = "Endpoints para gerenciamento de perfis de usuário")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obter perfil por ID de usuário")
    public ResponseEntity<ProfileResponseDTO> getProfileByUserId(@PathVariable Integer userId) {
        ProfileResponseDTO profile = profileService.getProfileByUserId(userId);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/user/{userId}")
    @Operation(summary = "Criar ou atualizar perfil por ID de usuário")
    public ResponseEntity<ProfileResponseDTO> createOrUpdateProfile(@PathVariable Integer userId, @Valid @RequestBody ProfileRequestDTO profileRequest) {
        ProfileResponseDTO updatedProfile = profileService.createOrUpdateProfile(userId, profileRequest);
        return ResponseEntity.ok(updatedProfile);
    }

}