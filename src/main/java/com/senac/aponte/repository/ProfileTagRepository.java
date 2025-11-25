package com.senac.aponte.repository;

import com.senac.aponte.entity.ProfileTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileTagRepository extends JpaRepository<ProfileTag, Integer> {
}
