package com.senac.aponte.controller;

import com.senac.aponte.dto.response.match.MatchResponseDTO;
import com.senac.aponte.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matches")
@Tag(name = "Matches", description = "Endpoints para obter o match do dia")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/today")
    @Operation(summary = "Obter o perfil do match do dia para o utilizador ligado")
    public ResponseEntity<MatchResponseDTO> getTodaysMatch(@RequestParam Integer userId) {
        // Numa aplicação real, o userId viria do token de segurança
        return ResponseEntity.ok(matchService.getTodaysMatch(userId));
    }

}