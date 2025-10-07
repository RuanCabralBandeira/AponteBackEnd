package com.senac.aponte.service;

import com.senac.aponte.dto.response.match.MatchResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    public MatchResponseDTO getTodaysMatch(Integer userId) {
        // O código abaixo é um placeholder para simular o funcionamento.
        System.out.println("Buscando match para o usuário: " + userId);
        return new MatchResponseDTO();
    }

}