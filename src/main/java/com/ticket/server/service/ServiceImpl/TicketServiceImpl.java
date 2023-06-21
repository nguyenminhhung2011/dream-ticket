package com.ticket.server.service.ServiceImpl;

import com.ticket.server.dtos.TicketDtos.AddTicketRequest;
import com.ticket.server.dtos.TicketDtos.TicketDto;
import com.ticket.server.dtos.TicketDtos.TicketFilterRequest;
import com.ticket.server.dtos.TicketDtos.TicketRequest;
import com.ticket.server.entities.*;
import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import com.ticket.server.model.GetListDataRequest;
import com.ticket.server.model.GetListDataResponse;
import com.ticket.server.model.Ticket;
import com.ticket.server.repository.FlightRepository;
import com.ticket.server.repository.PaymentRepository;
import com.ticket.server.repository.TicketInformationRepository;
import com.ticket.server.repository.TicketRepository;
import com.ticket.server.service.IService.ITicketService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements ITicketService {

    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;
    private final TicketInformationRepository  ticketInfoRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public boolean addTicket(AddTicketRequest request) throws Exception {
        final Optional<Flight> flightOptional = flightRepository.findById(request.getFlightId());

        if (flightOptional.isEmpty()){
            throw new Exception("Can not found any flight have id " + request.getFlightId());
        }

        final Flight flight = flightOptional.get();

        final List<TicketEntity> ticketEntities = new ArrayList<>();

        PaymentEntity paymentEntity = PaymentEntity
                .builder()
                .createdDate(Date.from(Instant.now()).getTime())
                .paymentType(request.getPaymentType())
                .build();

        paymentRepository.save(paymentEntity);

        request.getTickets().forEach(ticketRequest -> {
            final Optional<TicketInformationEntity> optionalTicketInfoEntity =  ticketInfoRepository
                    .findById(new TicketInformationEntityId(ticketRequest.getTicketType(),flight));

            if (optionalTicketInfoEntity.isEmpty()){
                try {
                    throw new Exception("Can not found any flight have id " + request.getFlightId() + " and have ticketType = " + ticketRequest.getTicketType());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            final TicketEntity ticketEntity = TicketEntity
                    .builder()
                    .name(ticketRequest.getName())
                    .gender(ticketRequest.getGender())
                    .dob(Date.from(Instant.ofEpochMilli(ticketRequest.getDob())))
                    .emailAddress(ticketRequest.getEmailAddress())
                    .seat(ticketRequest.getSeat())
                    .timeBought(Date.from(Instant.now()))
                    .phoneNumber(ticketRequest.getPhoneNumber())
                    .luggage(ticketRequest.getLuggage())
                    .ticketInformation(optionalTicketInfoEntity.get())
                    .flight(flight)
                    .payment(paymentEntity)
                    .build();

            ticketEntities.add(ticketEntity);
        });
//        paymentEntity.setCustomers(CustomerEntity.builder().na.build());

        final List<TicketEntity> result =  ticketRepository.saveAll(ticketEntities);

        return true;
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
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public TicketDto updateTicket(Long id, TicketRequest request) throws Exception {

        if (!ticketRepository.existsById(id)){
            throw new Exception("Can not found ticket entry corresponding");
        }

        final Optional<TicketEntity> optionalTicketEntity = ticketRepository.findById(id);
        final TicketEntity ticketEntity = optionalTicketEntity.get();

        final TicketEntity newTicketEntity = ticketRepository.save(
                TicketEntity
                        .builder()
                        .id(ticketEntity.getId())
                        .payment(ticketEntity.getPayment())
                        .flight(ticketEntity.getFlight())
                        .ticketInformation(ticketEntity.getTicketInformation())
                        .name(request.getName())
                        .gender(request.getGender())
                        .luggage(request.getLuggage())
                        .seat(request.getSeat())
                        .phoneNumber(request.getPhoneNumber())
                        .timeBought(Date.from(Instant.ofEpochMilli(request.getTimeBought())))
                        .emailAddress(request.getEmailAddress())
                        .dob(Date.from(Instant.ofEpochMilli(request.getDob())))
                        .build()
        );

        return TicketDto.fromEntity(newTicketEntity);
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
    public GetListDataResponse<TicketDto> getTicketByFilter(TicketFilterRequest request) {
//        Sort sort = !request.isAscending() ? new Sort().descending() : new Sort().ascending();

        return null;
    }

}
