package com.senac.aponte.controller;

import com.senac.aponte.dto.response.tag.TagResponseDTO;
import com.senac.aponte.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@Tag(name = "Tags", description = "Endpoints para gestão de tags de interesse")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    @Operation(summary = "Listar todas as tags disponíveis")
    public ResponseEntity<List<TagResponseDTO>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @PostMapping("/profile/{profileId}/add/{tagId}")
    @Operation(summary = "Adicionar uma tag a um perfil")
    public ResponseEntity<Void> addTagToProfile(@PathVariable Integer profileId, @PathVariable Integer tagId) {
        tagService.addTagToProfile(profileId, tagId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}