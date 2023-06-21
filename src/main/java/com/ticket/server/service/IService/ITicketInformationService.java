package com.ticket.server.service.IService;

import com.ticket.server.entities.Flight;
import com.ticket.server.entities.TicketInformationEntity;

import java.util.List;

public interface ITicketInformationService {
    TicketInformationEntity addTicketInformation(TicketInformationEntity flight);
    List<TicketInformationEntity> getTicketInformationByFlight(Long id);

}
