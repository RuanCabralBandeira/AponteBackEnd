package com.senac.aponte.service;

import com.senac.aponte.dto.response.match.MatchResponseDTO;
import com.senac.aponte.dto.response.profile.ProfileResponseDTO;
import com.senac.aponte.entity.Match;
import com.senac.aponte.entity.Profile;
import com.senac.aponte.entity.User;
import com.senac.aponte.repository.MatchRepository;
import com.senac.aponte.repository.ProfileRepository;
import com.senac.aponte.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public MatchService(MatchRepository matchRepository, ProfileRepository profileRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.matchRepository = matchRepository;
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Lógica principal do CU03: Obter o Match do Dia.
     * @param userId O ID do utilizador autenticado.
     * @return O DTO do Match.
     */
    public MatchResponseDTO getTodaysMatch(Integer userId) {
        LocalDate today = LocalDate.now();

        // 1. Verifica se o usuário logado JÁ TEM um match hoje
        Optional<Match> existingMatchOpt = matchRepository.findActiveMatchForUser(userId, today);
        if (existingMatchOpt.isPresent()) {
            Match match = existingMatchOpt.get();
            User matchedUser = (match.getUser1().getId().equals(userId)) ? match.getUser2() : match.getUser1();
            Profile matchedProfile = profileRepository.findByUserId(matchedUser.getId())
                    .orElseThrow(() -> new RuntimeException("Perfil do match não encontrado."));
            return convertToMatchResponseDTO(match, matchedProfile);
        }

        User currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado."));

        // 2. Lista de quem EU já dei match antes (Histórico)
        List<Integer> excludedUserIds = matchRepository.findAllMatchedUserIds(userId);

        // 3. --- NOVA LÓGICA: Bloquear quem JÁ ESTÁ OCUPADO hoje com outra pessoa ---
        List<Match> todaysMatches = matchRepository.findAllMatchesByDate(today);
        for (Match m : todaysMatches) {
            excludedUserIds.add(m.getUser1().getId());
            excludedUserIds.add(m.getUser2().getId());
        }

        // 4. Adiciona o próprio usuário à lista de excluídos
        excludedUserIds.add(userId);

        // 5. Busca candidatos que NÃO estejam na lista de excluídos
        List<Profile> candidates = profileRepository.findByUser_IdNotIn(excludedUserIds);

        if (candidates.isEmpty()) {
            throw new RuntimeException("Nenhum candidato disponível para hoje. Tente amanhã!");
        }

        // 6. Sorteia um e cria o match
        Collections.shuffle(candidates);
        Profile newMatchedProfile = candidates.get(0);
        User newMatchedUser = newMatchedProfile.getUser();

        Match newMatch = new Match();
        newMatch.setUser1(currentUser);
        newMatch.setUser2(newMatchedUser);


        newMatch.setExpiresAt(today.plusDays(1));

        Match savedMatch = matchRepository.save(newMatch);

        return convertToMatchResponseDTO(savedMatch, newMatchedProfile);
    }


    private MatchResponseDTO convertToMatchResponseDTO(Match match, Profile matchedProfile) {
        MatchResponseDTO dto = new MatchResponseDTO();
        dto.setId(match.getId());
        dto.setExpiresAt(match.getExpiresAt().atTime(23, 59, 59)); // Envia data e hora


        ProfileResponseDTO profileDTO = modelMapper.map(matchedProfile, ProfileResponseDTO.class);
        profileDTO.setUserId(matchedProfile.getUser().getId());

        dto.setMatchedProfile(profileDTO);

        return dto;
    }
}