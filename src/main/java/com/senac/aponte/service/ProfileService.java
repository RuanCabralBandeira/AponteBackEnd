package com.senac.aponte.service;

import com.senac.aponte.dto.request.profile.ProfileRequestDTO;
import com.senac.aponte.dto.response.profile.ProfileResponseDTO;
import com.senac.aponte.entity.Profile;
import com.senac.aponte.entity.User;
import com.senac.aponte.repository.ProfileRepository;
import com.senac.aponte.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public ProfileResponseDTO getProfileByUserId(Integer userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user ID: " + userId));

        ProfileResponseDTO response = modelMapper.map(profile, ProfileResponseDTO.class);
        response.setUserId(profile.getUser().getId());
        return response;
    }

    public ProfileResponseDTO createOrUpdateProfile(Integer userId, ProfileRequestDTO profileRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Profile profile = profileRepository.findByUserId(userId).orElse(new Profile());

        modelMapper.map(profileRequest, profile);
        profile.setUser(user);

        Profile savedProfile = profileRepository.save(profile);

        ProfileResponseDTO response = modelMapper.map(savedProfile, ProfileResponseDTO.class);
        response.setUserId(savedProfile.getUser().getId());
        return response;
    }

}