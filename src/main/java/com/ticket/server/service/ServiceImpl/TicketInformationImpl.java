package com.ticket.server.service.ServiceImpl;

import com.ticket.server.entities.TicketInformationEntity;
import com.ticket.server.repository.TicketInformationRepository;
import com.ticket.server.service.IService.ITicketInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@RequiredArgsConstructor

public class TicketInformationImpl implements ITicketInformationService {
    private  final TicketInformationRepository ticketInformationRepository;
    @Override
    public TicketInformationEntity addTicketInformation(TicketInformationEntity flight) {
        return ticketInformationRepository.save(flight);
    }

    @Override
    public List<TicketInformationEntity> getTicketInformationByFlight(Long id) {
        return ticketInformationRepository.findAllByFlight(id);
    }
}
