package com.senac.aponte.service;

import com.senac.aponte.dto.request.message.MessageRequestDTO;
import com.senac.aponte.dto.response.message.MessageResponseDTO;
import com.senac.aponte.entity.Match;
import com.senac.aponte.entity.Message;
import com.senac.aponte.entity.User;
import com.senac.aponte.repository.MatchRepository;
import com.senac.aponte.repository.MessageRepository;
import com.senac.aponte.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public MessageService(MessageRepository messageRepository, MatchRepository matchRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<MessageResponseDTO> getMessagesForMatch(Integer matchId) {
        return messageRepository.findByMatchIdOrderBySentAtAsc(matchId).stream()
                .map(message -> modelMapper.map(message, MessageResponseDTO.class))
                .collect(Collectors.toList());
    }

    public MessageResponseDTO sendMessage(Integer matchId, MessageRequestDTO messageRequest) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found"));
        User sender = userRepository.findById(messageRequest.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(messageRequest.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));
        Message message = new Message();
        message.setText(messageRequest.getText());
        message.setMatch(match);
        message.setSender(sender);
        message.setReceiver(receiver);
        Message savedMessage = messageRepository.save(message);
        return modelMapper.map(savedMessage, MessageResponseDTO.class);
    }

}