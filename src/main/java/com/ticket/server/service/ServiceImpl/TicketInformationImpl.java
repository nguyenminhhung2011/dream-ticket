package com.ticket.server.service.ServiceImpl;

import com.ticket.server.dtos.TicInformationDto.AddTicInformationRequest;
import com.ticket.server.entities.Flight;
import com.ticket.server.entities.TicketInformationEntity;
import com.ticket.server.entities.TicketInformationEntityId;
import com.ticket.server.repository.FlightRepository;
import com.ticket.server.repository.TicketInformationRepository;
import com.ticket.server.service.IService.ITicketInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor

public class TicketInformationImpl implements ITicketInformationService {
    private  final TicketInformationRepository ticketInformationRepository;
    private  final FlightRepository flightRepository;

    @Override
    public List<TicketInformationEntity> getTicketInformationByFlight(Long id) {
        return ticketInformationRepository.findAllByFlight(id);
    }

    @Override
    public boolean addTicketInformation(AddTicInformationRequest request) throws Exception {
        final Optional<Flight> flight = flightRepository.findById(request.getFlightId());
        final List<TicketInformationEntity> ticketInformationEntities =  new ArrayList<>();
        if(flight.isEmpty()){
            throw  new Exception("Flight is not found");
        }
        request.getTicketInformation().forEach(ticketInformationEntity -> {
            TicketInformationEntity ticketInformation = TicketInformationEntity
                    .builder()
                    .price(ticketInformationEntity.getPrice())
                    .id(new TicketInformationEntityId(
                            ticketInformationEntity.getId().getTicketType(),
                            flight.get() // Set the flight entity here
                    ))
                    .quantity(ticketInformationEntity.getQuantity())
                    .seatHeader(ticketInformationEntity.getSeatHeader())
                    .seatPosition(ticketInformationEntity.getSeatPosition())
                    .build();
            ticketInformationEntities.add(ticketInformation);
        });

        return !ticketInformationRepository.saveAll(ticketInformationEntities).isEmpty();
    }

    @Override
    public boolean deleteTicInformation(long id) {
        try{
//            final Optional<TicketInformationEntity> optionalTicketInformation = ticketInformationRepository.findById(id);
//            if(optionalTicketInformation.isPresent()){
//                ticketInformationRepository.deleteById(TicketInformationEntityId);
//            }
            return false;
        } catch (Exception e){
            throw new RuntimeException();
        }
    }
}
