package com.ticket.server.service.ServiceImpl;

import com.ticket.server.entities.Flight;
import com.ticket.server.entities.PaymentEntity;
import com.ticket.server.entities.TicketInformationEntity;
import com.ticket.server.repository.FlightRepository;
import com.ticket.server.repository.PaymentRepository;
import com.ticket.server.repository.TicketInformationRepository;
import com.ticket.server.service.IService.IFlightService;
import com.ticket.server.service.IService.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FlightServicesImpl implements IFlightService {
    private  final FlightRepository flightRepository;
    private final PaymentRepository paymentRepository;

    private final TicketInformationRepository ticketInformationRepository;
    private final IPaymentService paymentService;



    @Override
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Optional<Flight> getFlight(Long id) {
        return flightRepository.findById(id);
    }

    @Override
    public List<Flight> getAllFlight() {
        return flightRepository.findAll();
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
    public Optional<Flight> updateFlight(Long id, Flight flight) {
        if(!flightRepository.existsById(id)){
            return Optional.empty();
        }
        flight.setId(id);
        return Optional.of(flightRepository.save(flight));
    }

    @Override
    public List<Flight> searchFlights(String departureKeyword, String arrivalKeyword, String arrivalTimeKeyword) {
        return flightRepository.findByDepartureAirportContainingOrArrivalAirportContainingAndArrivalTimeContaining(departureKeyword, arrivalKeyword, arrivalTimeKeyword);
    }

    @Override
    public List<Flight> getFlightsSortedBy(String sortBy, boolean ascending) {
        Sort.Direction direction = ascending ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
        return flightRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Flight> getFlightByPage(int cursor, int pageSize) {
        PageRequest pageable = PageRequest.of(cursor, pageSize);
        Page<Flight> page = flightRepository.findAll(pageable);
        return page.getContent();
    }

    @Override
    public Integer getPages(int pageSize) {
        long flightCount = flightRepository.count();
        return (Integer) (int) Math.ceil((double) flightCount/ pageSize);    }

    @Override
    public List<Flight> filterFlight(String locationArrival,
                                     String locationDeparture,
                                     String airlineName,
                                     Integer limit,
                                     Integer offset) {
        return flightRepository.filterFlight(locationArrival,
                                            locationDeparture,
                                            airlineName,
                                            limit,
                                            offset);
    }

    @Override
    public List<Flight> getFlightWithArrivalId(Integer id) {
        return flightRepository.getFlightWithArrivalId(id);
    }

    @Override
    public List<Flight> getFlightWithDepartureId(Integer id) {
        return flightRepository.getFlightDepartureId(id);
    }

    @Override
    public List<Flight> getFlightByAirportId(Integer id) {
        return flightRepository.getFlightByAirportId(id);
    }
}
