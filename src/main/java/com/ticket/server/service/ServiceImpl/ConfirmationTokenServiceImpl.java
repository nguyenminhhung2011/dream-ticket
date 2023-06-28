package com.ticket.server.service.ServiceImpl;

import com.ticket.server.model.ConfirmationToken;
import com.ticket.server.repository.ConfirmationTokenRepository;
import com.ticket.server.service.IService.IConfirmationToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements IConfirmationToken {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    @Override
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    @Override
    public int setConfirmAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(token);
    }
}
