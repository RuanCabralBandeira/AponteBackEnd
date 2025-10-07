package com.senac.aponte.service;

import com.senac.aponte.dto.response.tag.TagResponseDTO;
import com.senac.aponte.entity.Profile;
import com.senac.aponte.entity.ProfileTag;
import com.senac.aponte.entity.Tag;
import com.senac.aponte.repository.ProfileRepository;
import com.senac.aponte.repository.ProfileTagRepository;
import com.senac.aponte.repository.TagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final ProfileRepository profileRepository;
    private final ProfileTagRepository profileTagRepository;
    private final ModelMapper modelMapper;

    public TagService(TagRepository tagRepository, ProfileRepository profileRepository, ProfileTagRepository profileTagRepository, ModelMapper modelMapper) {
        this.tagRepository = tagRepository;
        this.profileRepository = profileRepository;
        this.profileTagRepository = profileTagRepository;
        this.modelMapper = modelMapper;
    }

    public List<TagResponseDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tag -> modelMapper.map(tag, TagResponseDTO.class))
                .collect(Collectors.toList());
    }

    public void addTagToProfile(Integer profileId, Integer tagId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        ProfileTag profileTag = new ProfileTag();
        profileTag.setProfile(profile);
        profileTag.setTag(tag);
        profileTagRepository.save(profileTag);
    }

}