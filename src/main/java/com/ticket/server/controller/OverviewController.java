package com.ticket.server.controller;

import com.ticket.server.dtos.OverViewDto.OverviewModelDataDto;
import com.ticket.server.dtos.Payment.PaymentDto;
import com.ticket.server.dtos.Payment.PaymentManagementPage.TicketTierData;
import com.ticket.server.entities.TotalByDateRange;
import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.repository.AirportRepository;
import com.ticket.server.repository.CustomerRepository;
import com.ticket.server.repository.FlightRepository;
import com.ticket.server.repository.PaymentRepository;
import com.ticket.server.service.IService.IPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/overview")
public class OverviewController {

    private final CustomerRepository customerRepository;
    private final IPaymentService paymentService;
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final PaymentRepository paymentRepository;
    @GetMapping()
    public ResponseEntity<?> fetchOverviewData(
            @RequestParam long from,
            @RequestParam long to
    ){
        try {
            long totalCustomer = customerRepository.count();
            long totalFlight = flightRepository.count();
            long totalAirport = airportRepository.count();
            long totalPayment = paymentRepository.count();

            final List<PaymentDto> payment = paymentService.getPaymentByDateRange(from,to);

            AtomicLong economy = new AtomicLong();
            AtomicLong premiumEconomy = new AtomicLong();
            AtomicLong business = new AtomicLong();
            AtomicLong first = new AtomicLong();


            for (PaymentDto paymentDto : payment){
                paymentDto.getTicket().forEach(ticketDto -> {
                    switch (ticketDto.getType()){
                        case 0:
                            economy.getAndIncrement();
                            break;
                        case 1:
                            business.getAndIncrement();
                            break;
                        case 2:
                            premiumEconomy.getAndIncrement();
                            break;
                        case 3:
                            first.getAndIncrement();
                            break;
                    }
                });
            }

            final Date fromDate = new Date(from);
            final Date toDate = new Date(to);

            final LocalDate fromLocalDate = fromDate.toLocalDate();
            final LocalDate toLocalDate = toDate.toLocalDate();

            long range = toLocalDate.toEpochDay() - fromLocalDate.toEpochDay() + 1;

            System.out.println("range" + range);

            final List<TotalByDateRange> flightByWeekEntity =
                    flightRepository.countFlightByDateRange(fromDate,toDate)
                            .stream()
                            .map(tuple -> new TotalByDateRange(tuple.get(0, Timestamp.class),tuple.get(1,Long.class)))
                            .toList();
            Map<Long,Long> flightByWeek = new HashMap<>();
            flightByWeekEntity.forEach(totalByDateRange -> {
                flightByWeek.put(totalByDateRange.getDate().toEpochDay(),totalByDateRange.getTotal());
            });

            final List<TotalByDateRange> customerByWeeKEntity =
                    paymentRepository.countPaymentCustomerByDateRange(fromDate,toDate)
                            .stream()
                            .map(tuple -> new TotalByDateRange(tuple.get(0,Timestamp.class),tuple.get(1,Long.class)))
                            .toList();;
            Map<Long,Long> customerByWeek = new HashMap<>();
            customerByWeeKEntity.forEach(totalByDateRange -> {
                customerByWeek.put(totalByDateRange.getDate().toEpochDay(),totalByDateRange.getTotal());
            });

            final List<TotalByDateRange> paymentPendingByWeekEntity =
                            paymentRepository
                            .countPaymentStatusByDateRange(PaymentStatus.PENDING.name(),fromDate,toDate)
                                    .stream()
                                    .map(tuple -> new TotalByDateRange(tuple.get(0,Timestamp.class),tuple.get(1,Long.class)))
                                    .toList();;
            Map<Long,Long> paymentPendingByWeek = new HashMap<>();
            paymentPendingByWeekEntity.forEach(totalByDateRange -> {
                paymentPendingByWeek.put(totalByDateRange.getDate().toEpochDay(),totalByDateRange.getTotal());
            });

            final List<TotalByDateRange> paymentSuccessByWeekEntity =
                    paymentRepository
                            .countPaymentStatusByDateRange(PaymentStatus.SUCCEEDED.name(),fromDate,toDate)
                            .stream()
                            .map(tuple -> new TotalByDateRange(tuple.get(0,Timestamp.class),tuple.get(1,Long.class)))
                            .toList();
            Map<Long,Long> paymentSuccessByWeek = new HashMap<>();
            paymentSuccessByWeekEntity.forEach(totalByDateRange -> {
                paymentSuccessByWeek.put(totalByDateRange.getDate().toEpochDay(),totalByDateRange.getTotal());
            });


            for (int i = 0;i<range;i++){
                final LocalDate time = fromLocalDate.plusDays(i);
                if (!flightByWeek.containsKey(time.toEpochDay())){
                    flightByWeek.put(time.toEpochDay(),0L);
                }

                if (!customerByWeek.containsKey(time.toEpochDay())){
                    customerByWeek.put(time.toEpochDay(),0L);
                }

                if (!paymentPendingByWeek.containsKey(time.toEpochDay())){
                    paymentPendingByWeek.put(time.toEpochDay(),0L);
                }
                if (!paymentSuccessByWeek.containsKey(time.toEpochDay())){
                    paymentSuccessByWeek.put(time.toEpochDay(),0L);
                }
            }

            final List<Map<String, Long>> paymentStatisticData = new ArrayList<>();
            final List<Long> listPendingPayment = paymentPendingByWeek.values().stream().toList();
            final List<Long> listSuccessPayment = paymentSuccessByWeek.values().stream().toList();
            for (int i =0 ;i<range ;i++){
                Map<String, Long> temp = new HashMap<>();
                temp.put("y1",listPendingPayment.get(i));
                temp.put("y2",listSuccessPayment.get(i));

                paymentStatisticData.add(temp);
            }

            final TicketTierData ticketTierData = TicketTierData
                    .builder()
                    .business(business.get())
                    .economy(economy.get())
                    .first(first.get())
                    .premiumEconomy(premiumEconomy.get())
                    .build();

            return ResponseEntity.ok(
                    OverviewModelDataDto
                            .builder()
                            .ticketTierData(ticketTierData)
                            .totalAirport(totalAirport)
                            .totalCustomer(totalCustomer)
                            .totalFlight(totalFlight)
                            .totalPayment(totalPayment)
                            .flightByWeek(flightByWeek.values().stream().toList())
                            .customerByWeek(customerByWeek.values().stream().toList())
                            .paymentByWeek(paymentStatisticData)
                            .build()
            );


        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
