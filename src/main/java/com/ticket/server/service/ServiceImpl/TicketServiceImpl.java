package com.ticket.server.service.ServiceImpl;

import com.ticket.server.dtos.TicketDtos.AddTicketRequest;
import com.ticket.server.dtos.TicketDtos.TicketDto;
import com.ticket.server.entities.TicketEntity;
import com.ticket.server.model.GetListDataRequest;
import com.ticket.server.model.GetListDataResponse;
import com.ticket.server.model.Ticket;
import com.ticket.server.repository.TicketRepository;
import com.ticket.server.service.IService.ITicketService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.net.URI;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements ITicketService {

    private final TicketRepository ticketRepository;

    @Override
    public TicketDto addTicket(AddTicketRequest request) {
        final TicketEntity ticketEntity = TicketEntity
                .builder()
                .name(request.getName())
                .gender(request.getGender())
                .dob(Date.from(Instant.ofEpochMilli(request.getDob())))
                .emailAddress(request.getEmailAddress())
                .seat(request.getSeat())
                .timeBought(Date.from(Instant.now()))
                .phoneNumber(request.getPhoneNumber())
                .luggage(request.getLuggage())
                .build();

        final TicketEntity result =  ticketRepository.save(ticketEntity);

        return TicketDto.fromEntity(result);
    }

    @Override
    public TicketDto getTicket(Long id) throws Exception {
        final Optional<TicketEntity> optionalTicketEntity = ticketRepository.findById(id);
        if (optionalTicketEntity.isPresent()){
            return TicketDto.fromEntity(optionalTicketEntity.get());
        }
        else{
            throw new Exception("Can not found ticket corresponding");
        }
    }

    @Override
    public List<TicketDto> getAllTicket() {
        return ticketRepository.findAll().stream().map(TicketDto::fromEntity).toList();
    }

    @Override
    public boolean deleteTicket(Long id) {
        return false;
    }

    @Override
    public TicketDto updateTicket(Long id, AddTicketRequest request) {
        return null;
    }

    @Override
    public GetListDataResponse<TicketDto> getTicketByPage(int page,int perPage) {
        final long total = ticketRepository.count();

        final PageRequest pageRequest = PageRequest.of(page, perPage);

        final Page<TicketEntity> ticketEntityPage = ticketRepository.findAll(pageRequest);
        final List<TicketDto> data = ticketEntityPage.getContent().stream().map(TicketDto::fromEntity).toList();

        return new GetListDataResponse<>(total,data);
    }

    @Override
    public List<TicketDto> getListTicket(GetListDataRequest request) {
        return null;
    }


}
