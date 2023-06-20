package com.ticket.server.service.IService;

import com.ticket.server.dtos.TicketDtos.AddTicketRequest;
import com.ticket.server.dtos.TicketDtos.TicketDto;
import com.ticket.server.model.GetListDataRequest;
import com.ticket.server.model.GetListDataResponse;
import com.ticket.server.model.Ticket;
import com.ticket.server.repository.TicketRepository;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public interface ITicketService {
    TicketDto addTicket(AddTicketRequest request) throws Exception;

    TicketDto getTicket(Long id) throws Exception;

    List<TicketDto> getAllTicket();

    boolean deleteTicket(Long id);

    TicketDto updateTicket(Long id, AddTicketRequest request);

    GetListDataResponse<TicketDto> getTicketByPage(int page, int perPage);
}
