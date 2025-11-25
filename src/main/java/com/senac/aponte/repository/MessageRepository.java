package com.senac.aponte.repository;

import com.senac.aponte.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByMatchIdOrderBySentAtAsc(Integer matchId);
}
