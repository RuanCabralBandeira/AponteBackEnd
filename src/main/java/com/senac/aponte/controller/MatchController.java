package com.senac.aponte.controller;

import com.senac.aponte.dto.response.match.MatchResponseDTO;
import com.senac.aponte.entity.User; // Importar a entidade User
import com.senac.aponte.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal; // Importar
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matches")
@Tag(name = "Matches", description = "Endpoints para obter o match do dia")
@SecurityRequirement(name = "bearerAuth") // Protege todos os endpoints neste controller
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/today")
    @Operation(summary = "Obter o perfil do match do dia para o utilizador ligado")
    // ATUALIZAÇÃO: Trocamos @RequestParam pelo utilizador autenticado
    public ResponseEntity<MatchResponseDTO> getTodaysMatch(@AuthenticationPrincipal User userDetails) {
        // O Spring Security injeta o utilizador logado automaticamente.
        Integer userId = userDetails.getId();

        // Chamamos o serviço com o ID seguro
        MatchResponseDTO match = matchService.getTodaysMatch(userId);

        return ResponseEntity.ok(match);
    }

}