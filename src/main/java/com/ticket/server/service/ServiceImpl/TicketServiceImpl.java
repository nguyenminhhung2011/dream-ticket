package com.ticket.server.service.ServiceImpl;

import com.ticket.server.dtos.Payment.PaymentDto;
import com.ticket.server.dtos.TicketDtos.AddTicketRequest;
import com.ticket.server.dtos.TicketDtos.TicketDto;
import com.ticket.server.dtos.TicketDtos.TicketFilterRequest;
import com.ticket.server.dtos.TicketDtos.TicketRequest;
import com.ticket.server.entities.*;
import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import com.ticket.server.exceptions.NotFoundException;
import com.ticket.server.model.GetListDataResponse;
import com.ticket.server.repository.*;
import com.ticket.server.service.IService.ITicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements ITicketService {

    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;
    private final TicketInformationRepository  ticketInfoRepository;
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;

    @Override
    public PaymentDto addTicket(AddTicketRequest request)  {
        final Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(request.getCustomerId());
        final Optional<Flight> optionalFlight = flightRepository.findById(request.getFlightId());

        if (optionalCustomerEntity.isEmpty()){
            throw new NotFoundException("Can not found any customer have id " + request.getCustomerId());
        }

        if (optionalFlight.isEmpty()){
            throw new NotFoundException("Can not found any flight have id " + request.getFlightId());
        }

        final CustomerEntity customer = optionalCustomerEntity.get();
        final Flight flight = optionalFlight.get();

        final List<TicketEntity> ticketEntities = new ArrayList<>();

        AtomicReference<Double> total = new AtomicReference<>(0.0);

        PaymentEntity paymentEntity = PaymentEntity
                .builder()
                .createdDate(new java.sql.Date(Instant.now().toEpochMilli()))
                .total(total.get())
                .status(PaymentStatus.PENDING)
                .paymentType(PaymentType.CARD)
                .customers(customer)
                .flight(flight)
                .build();

        final PaymentEntity savedPayment = paymentRepository.save(paymentEntity);

        request.getTickets().forEach(ticketRequest -> {
            final Optional<TicketInformationEntity> optionalTicketInfoEntity =  ticketInfoRepository
                    .findByFlightAndType(request.getFlightId(),ticketRequest.getTicketType());

            if (optionalTicketInfoEntity.isEmpty()){
                throw new NotFoundException("Can not found any ticket information have id " + request.getFlightId() + " and have ticketType = " + ticketRequest.getTicketType());
            }

            final TicketInformationEntity ticketInformationEntity = optionalTicketInfoEntity.get();

            total.updateAndGet(v -> v + ticketInformationEntity.getPrice());

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
                    .customer(customer)
                    .payment(savedPayment)
                    .ticketInformation(ticketInformationEntity)
                    .build();

            ticketEntities.add(ticketEntity);
        });

        final List<TicketEntity> tickets = ticketRepository.saveAll(ticketEntities);

        savedPayment.setTicket(tickets);

        return PaymentDto.fromEntity(savedPayment);
    }

    @Override
    public TicketDto getTicket(Long id){
        final Optional<TicketEntity> optionalTicketEntity = ticketRepository.findById(id);
        if (optionalTicketEntity.isPresent()){
            return TicketDto.fromEntity(optionalTicketEntity.get());
        }
        else{
            throw new NotFoundException("Can not found ticket corresponding");
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
    public TicketDto updateTicket(Long id, TicketRequest request) {

        if (!ticketRepository.existsById(id)){
            throw new NotFoundException("Can not found ticket entry corresponding");
        }

        final Optional<TicketEntity> optionalTicketEntity = ticketRepository.findById(id);
        final TicketEntity ticketEntity = optionalTicketEntity.get();

        final TicketEntity newTicketEntity = ticketRepository.save(
                TicketEntity
                        .builder()
                        .id(ticketEntity.getId())
//                        .payment(ticketEntity.getPayment())
//                        .flight(ticketEntity.getFlight())
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
