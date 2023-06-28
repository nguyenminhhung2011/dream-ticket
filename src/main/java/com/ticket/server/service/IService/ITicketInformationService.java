package com.ticket.server.service.IService;

import com.ticket.server.dtos.TicInformationDto.AddTicInformationRequest;
import com.ticket.server.entities.TicketInformationEntity;

import java.util.List;

public interface ITicketInformationService {
    List<TicketInformationEntity> getTicketInformationByFlight(Long id);

    boolean addTicketInformation(AddTicInformationRequest request) throws Exception;

    public boolean deleteTicInformation(long id);

}
