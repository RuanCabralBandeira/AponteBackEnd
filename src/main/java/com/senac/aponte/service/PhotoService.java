package com.senac.aponte.service;

import com.senac.aponte.dto.request.photo.PhotoRequestDTO;
import com.senac.aponte.dto.response.photo.PhotoResponseDTO;
import com.senac.aponte.entity.Photo;
import com.senac.aponte.entity.Profile;
import com.senac.aponte.repository.PhotoRepository;
import com.senac.aponte.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;

    public PhotoService(PhotoRepository photoRepository, ProfileRepository profileRepository, ModelMapper modelMapper) {
        this.photoRepository = photoRepository;
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
    }

    public PhotoResponseDTO addPhotoToProfile(Integer profileId, PhotoRequestDTO photoRequest) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + profileId));

        Photo photo = modelMapper.map(photoRequest, Photo.class);
        photo.setProfile(profile);

        Photo savedPhoto = photoRepository.save(photo);
        return modelMapper.map(savedPhoto, PhotoResponseDTO.class);
    }

    public List<PhotoResponseDTO> getPhotosByProfileId(Integer profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + profileId));

        return profile.getPhotos().stream()
                .map(photo -> modelMapper.map(photo, PhotoResponseDTO.class))
                .collect(Collectors.toList());
    }

    public void deletePhoto(Integer photoId) {
        if (!photoRepository.existsById(photoId)) {
            throw new RuntimeException("Photo not found with ID: " + photoId);
        }
        photoRepository.deleteById(photoId);
    }
}
