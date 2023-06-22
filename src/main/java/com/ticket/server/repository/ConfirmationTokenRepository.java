package com.ticket.server.repository;

import com.ticket.server.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    public Optional<ConfirmationToken> findByToken(String token);
    @Transactional
    @Modifying
    @Query("Update ConfirmationToken c "+
        "Set c.confirmAt = ?2" +
        "Where c.token = ?1")
    public int updateConfirmedAt(String token);
}
