package com.ticket.server.service.ServiceImpl;

import com.ticket.server.entities.Airline;
import com.ticket.server.repository.AirlineRepository;
import com.ticket.server.service.IService.IAirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServicesImpl implements IAirlineService {
    @Autowired
    private final AirlineRepository airlineRepository;

    public AirlineServicesImpl(AirlineRepository airlineRepository) {
        this.airlineRepository =  airlineRepository;
    }

    @Override
    public List<Airline> getAllAirline() {
        return airlineRepository.findAll();
    }
}
