package com.senac.aponte.repository;

import com.senac.aponte.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    Optional<Photo> findFirstByProfileId(Integer profileId);
}