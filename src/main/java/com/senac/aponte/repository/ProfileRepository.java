package com.senac.aponte.repository;

import com.senac.aponte.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Optional<Profile> findByUserId(Integer userId);


    List<Profile> findByUser_IdNotIn(List<Integer> userIds);
}