package com.senac.aponte.controller;

import com.senac.aponte.dto.request.message.MessageRequestDTO;
import com.senac.aponte.dto.response.message.MessageResponseDTO;
import com.senac.aponte.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@Tag(name = "Mensagens", description = "Endpoints para o chat")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/match/{matchId}")
    @Operation(summary = "Obter o histórico de mensagens de um match")
    public ResponseEntity<List<MessageResponseDTO>> getMessages(@PathVariable Integer matchId) {
        return ResponseEntity.ok(messageService.getMessagesForMatch(matchId));
    }

    @PostMapping("/match/{matchId}")
    @Operation(summary = "Enviar uma nova mensagem para um match")
    public ResponseEntity<MessageResponseDTO> sendMessage(@PathVariable Integer matchId, @Valid @RequestBody MessageRequestDTO messageRequest) {
        MessageResponseDTO newMessage = messageService.sendMessage(matchId, messageRequest);
        return new ResponseEntity<>(newMessage, HttpStatus.CREATED);
    }

}