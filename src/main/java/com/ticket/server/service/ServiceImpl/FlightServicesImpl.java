package com.ticket.server.service.ServiceImpl;

import com.ticket.server.dtos.AirportDtos.StopResponse;
import com.ticket.server.dtos.FlightDtos.AddFlightDto;
import com.ticket.server.dtos.FlightDtos.EditFlightDto;
import com.ticket.server.dtos.FlightDtos.FlightDto;
import com.ticket.server.dtos.FlightDtos.FlightNotStopResponse;
import com.ticket.server.dtos.Payment.PaymentFlightTics;
import com.ticket.server.dtos.TicketDtos.TicketDto;
import com.ticket.server.entities.*;
import com.ticket.server.exceptions.NotFoundException;
import com.ticket.server.repository.*;
import com.ticket.server.service.IService.IFlightService;
import com.ticket.server.service.IService.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FlightServicesImpl implements IFlightService {
    private  final FlightRepository flightRepository;
    private final PaymentRepository paymentRepository;
    private final StopAirportRepository stopAirportRepository;

    private final TicketInformationRepository ticketInformationRepository;
    private final IPaymentService paymentService;
    private final FlightClassRepository flightClassRepository;


    private List<FlightDto> generateToFlightDto(List<Flight> flights){
        final List<FlightDto> flightsDto = new ArrayList<>();
        flights.forEach(flight -> {
            final List<StopAirport> stopGet =  stopAirportRepository.findAllByFlight(flight.getId());
            flightsDto.add(
                    new FlightDto(
                            flight,
                            stopGet.stream().map(StopResponse::new).toList()
                    )
            );
        });
        return flightsDto;
    }
    private List<FlightNotStopResponse> generateToFlightNotStops(List<Flight> flights){
        final List<FlightNotStopResponse> flightsDto = new ArrayList<>();
        flights.forEach(flight -> {
            flightsDto.add(
                    new FlightNotStopResponse(
                            flight
                    )
            );
        });
        return flightsDto;
    }

    @Override
    public FlightNotStopResponse addFlight(AddFlightDto addFlightDto) {
        try{
           Flight flight =  Flight
                   .builder()
                   .departureAirport(addFlightDto.getDepartureAirport())
                   .airline(addFlightDto.getAirline())
                   .arrivalAirport(addFlightDto.getArrivalAirport())
                   .arrivalTime(addFlightDto.getArrivalTime())
                   .departureTime(addFlightDto.getDepartureTime())
                   .build();
            var  addF = flightRepository.save(flight);
            final List<StopAirport> stopAirports =  new ArrayList<>();
            addFlightDto.getStopAirports().forEach(stopAirport -> {
                StopAirport s = StopAirport
                        .builder()
                        .description(stopAirport.getDescription())
                        .stopTime(stopAirport.getStopTime())
                        .id(new StopAirportId(
                                addF,
                                stopAirport.getAirport()
                        ))
                        .build();
                stopAirports.add(s);

            });
            var saveAll =  stopAirportRepository.saveAll(stopAirports);
            return new FlightNotStopResponse(addF);
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public FlightDto getFlight(Long id) {
        try{
            final Optional<Flight> flight = flightRepository.findById(id);
            if(flight.isEmpty()){
                throw new NotFoundException("Can not found flight");
            }
            final List<StopAirport> stopGet =  stopAirportRepository.findAllByFlight(flight.get().getId());
            return new FlightDto(
                    flight.get(),
                    stopGet.stream().map(StopResponse::new).toList()
            );
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<FlightNotStopResponse> getAllFlight() {
        try{
            final List<Flight> flights = flightRepository.findAll();
            return generateToFlightNotStops(flights);
        } catch (Exception e){
            throw new RuntimeException();
        }

    }

    @Override
    public List<FlightNotStopResponse> getFlightByDate(int day, int month, int year) {
        try {
            final List<Flight> flights = flightRepository.getFlightByDate(day, month, year);
            return generateToFlightNotStops(flights);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteFlight(Long id) {
       try{
           final Optional<Flight> optionalFlight = flightRepository.findById(id);
           if(optionalFlight.isEmpty()){
               return;
           }
           final List<PaymentEntity> listPayment =  paymentRepository.findByFlightId(id);
           for (var item :listPayment) {
               var deleted =  paymentService.deletePayment(item.getId());
               if(!deleted){
                   return;
               }
           }
           final List<StopAirport> stopAirports =  stopAirportRepository.findAllByFlight(id);
           for (var item: stopAirports) {
               stopAirportRepository.deleteById(item.getId());
           }
           final List<TicketInformationEntity> ticketInformationEntities =  ticketInformationRepository.findAllByFlight(id);
           for (var item: ticketInformationEntities) {
               ticketInformationRepository.deleteById(item.getId());
           }
           flightRepository.deleteById(id);
       } catch (Exception e){
            throw new RuntimeException();
       }
    }

    @Override
    public FlightNotStopResponse updateFlight(Long id, EditFlightDto editFlightDto) {
        try{
            if(!flightRepository.existsById(id)){
                throw new NotFoundException("Can not found flight");
            }
            editFlightDto.setId(Optional.of(id));
            var editF = flightRepository.save(
                    Flight
                            .builder()
                            .id(id)
                            .departureAirport(editFlightDto.getDepartureAirport())
                            .airline(editFlightDto.getAirline())
                            .arrivalAirport(editFlightDto.getArrivalAirport())
                            .arrivalTime(editFlightDto.getArrivalTime())
                            .departureTime(editFlightDto.getDepartureTime())
                            .build()
            );
            final List<StopAirport> stopAirports =  stopAirportRepository.findAllByFlight(id);
            for (var item: stopAirports) {
                stopAirportRepository.deleteById(item.getId());
            }
            stopAirports.clear();
            editFlightDto.getStopAirports().forEach(stopAirportRequest -> {
                stopAirports.add(StopAirport
                        .builder()
                        .description(stopAirportRequest.getDescription())
                        .stopTime(stopAirportRequest.getStopTime())
                        .id(new StopAirportId(
                                editF,
                                stopAirportRequest.getAirport()
                        ))
                        .build());
            });
            var saveAll = stopAirportRepository.saveAll(stopAirports);
            return new FlightNotStopResponse(editF);
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<FlightDto> searchFlights(String departureKeyword, String arrivalKeyword, String arrivalTimeKeyword) {
        return new ArrayList<>();
    }

    @Override
    public List<FlightDto> getFlightsSortedBy(String sortBy, boolean ascending) {
//        Sort.Direction direction = ascending ? Sort.Direction.ASC : Sort.Direction.DESC;
//        Sort sort = Sort.by(direction, sortBy);
//        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
//        return flightRepository.findAll(pageable).getContent();
        return new ArrayList<>();
    }

    @Override
    public List<FlightNotStopResponse> getFlightByPage(int cursor, int pageSize) {

        try{
            PageRequest pageable = PageRequest.of(cursor, pageSize);
            Page<Flight> page = flightRepository.findAll(pageable);
            return generateToFlightNotStops(page.getContent());
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public Integer getPages(int pageSize) {
        long flightCount = flightRepository.count();
        return (Integer) (int) Math.ceil((double) flightCount/ pageSize);    }

    @Override
    public PaymentFlightTics getFlightTicsFromPayment(long id) {
        try {
            final PaymentEntity paymentEntity = paymentService.getPaymentById(id);
            final FlightDto flightDto = getFlight(paymentEntity.getFlight().getId());
            return PaymentFlightTics
                    .builder()
                    .id(paymentEntity.getId())
                    .createdDate(paymentEntity.getCreatedDate().getTime())
                    .total(paymentEntity.getTotal())
                    .paymentStatus(paymentEntity.getStatus())
                    .paymentType(paymentEntity.getPaymentType())
                    .flight(flightDto)
                    .ticket(paymentEntity.getTicket().stream().map(TicketDto::fromEntity).toList())
                    .build();
        } catch (Exception e){
            throw  new RuntimeException();
        }
    }


    @Override
    public List<FlightNotStopResponse> filterFlight(String locationArrival,
                                     String locationDeparture,
                                     String airlineName,
                                     Integer limit,
                                     Integer offset) {
        try{
            final List<Flight> flights = flightRepository.filterFlight(locationArrival,
                    locationDeparture,
                    airlineName,
                    limit,
                    offset);
           return generateToFlightNotStops(flights);
        } catch (Exception e){
            throw new RuntimeException();
        }

    }

    @Override
    public List<FlightNotStopResponse> getFlightWithArrivalId(Integer id) {
        try{
            return generateToFlightNotStops(flightRepository.getFlightWithArrivalId(id));
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<FlightNotStopResponse> getFlightWithDepartureId(Integer id) {
        try{
            return generateToFlightNotStops(flightRepository.getFlightDepartureId(id));
        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<FlightNotStopResponse> getFlightByAirportId(Integer id) {
        try{
            return generateToFlightNotStops(flightRepository.getFlightByAirportId(id));
        } catch (Exception e){
            throw new RuntimeException();
        }
    }
}
