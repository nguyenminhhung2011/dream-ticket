package com.ticket.server.service.IService;

import com.ticket.server.model.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IConfirmationToken{
    public void saveConfirmationToken(ConfirmationToken token);
    public Optional<ConfirmationToken> getToken(String token);
    public int setConfirmAt(String token);
}
