package com.ticket.server.repository;

import com.ticket.server.model.AppToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppTokenRepository extends JpaRepository<AppToken,Long> {
    @Query(value = "select t from AppToken t inner join User u where u.id = :id and (t.isRevoke = false or t.isExpired = false)")
    List<AppToken> findALlValidTokenByUser(Long id);

    Optional<AppToken> findByToken(String token);
}
