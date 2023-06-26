package com.ticket.server.service.IService;

import com.ticket.server.dtos.Payment.PaymentDto;
import com.ticket.server.dtos.TicketDtos.AddTicketRequest;
import com.ticket.server.dtos.TicketDtos.TicketDto;
import com.ticket.server.dtos.TicketDtos.TicketFilterRequest;
import com.ticket.server.dtos.TicketDtos.TicketRequest;
import com.ticket.server.model.GetListDataRequest;
import com.ticket.server.model.GetListDataResponse;
import com.ticket.server.model.Ticket;
import com.ticket.server.repository.TicketRepository;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public interface ITicketService {
    PaymentDto addTicket(AddTicketRequest request) ;

    TicketDto getTicket(Long id) throws Exception;

    List<TicketDto> getAllTicket();
    List<TicketDto> getByFlight(Long flightId);
    void deleteTicket(Long id);

    TicketDto updateTicket(Long id, TicketRequest request);

    GetListDataResponse<TicketDto> getTicketByPage(int page, int perPage);

    GetListDataResponse<TicketDto> getTicketByFilter(TicketFilterRequest request);
}
