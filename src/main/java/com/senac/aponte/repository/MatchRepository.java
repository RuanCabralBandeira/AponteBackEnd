package com.senac.aponte.repository;

import com.senac.aponte.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {


    @Query("SELECT m FROM Match m WHERE (m.user1.id = :userId OR m.user2.id = :userId) AND m.matchDate = :today AND m.expiresAt >= :today")
    Optional<Match> findActiveMatchForUser(@Param("userId") Integer userId, @Param("today") LocalDate today);

    @Query("SELECT CASE WHEN m.user1.id = :userId THEN m.user2.id ELSE m.user1.id END FROM Match m WHERE m.user1.id = :userId OR m.user2.id = :userId")
    List<Integer> findAllMatchedUserIds(@Param("userId") Integer userId);

    @Query("SELECT m FROM Match m WHERE m.matchDate = :today")
    List<Match> findAllMatchesByDate(@Param("today") LocalDate today);
}