package com.ticket.server.service.ServiceImpl;

import com.ticket.server.dtos.Payment.*;
import com.ticket.server.dtos.Payment.PaymentManagementPage.*;
import com.ticket.server.dtos.TicketDtos.TicketDto;
import com.ticket.server.entities.PaymentEntity;
import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import com.ticket.server.exceptions.NotFoundException;
import com.ticket.server.repository.FlightRepository;
import com.ticket.server.repository.PaymentRepository;
import com.ticket.server.service.IService.IPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final FlightRepository flightRepository;

    @Override
    public List<PaymentDto> fetchPaymentData() {;
        return paymentRepository
                .findAll()
                .stream()
                .map(PaymentDto::fromEntity)
                .toList();
    }

    @Override
    public PaymentDto getLatestPaymentByCustomerId(long id) {
        Optional<PaymentEntity> paymentEntityOptional = paymentRepository.getPaymentByCustomerIdAndLatestCreateDate(id);

        if (paymentEntityOptional.isPresent()){
            return PaymentDto.fromEntity(paymentEntityOptional.get());
        }

        throw new NotFoundException("Can not found any valid payment");
    }



    @Override
    public PaymentManagementPageDto fetchPaymentManagementPage(){
        final Date now = new Date(Instant.now().toEpochMilli());

        final List<PaymentDto> paymentsToday = fetchAllPaymentByCreatedDate(Instant.now().toEpochMilli());

        int totalPaymentToday = paymentsToday.size();
        long totalCustomerToday= paymentRepository.countCustomerByDate(now);
        long totalFlightToday = flightRepository.countFlightByDate(new Date(Instant.now().toEpochMilli()));
        AtomicInteger totalPassengerToday = new AtomicInteger();

        AtomicReference<Double> amount = new AtomicReference<>(0.0);
        AtomicInteger cardTotal = new AtomicInteger();
        AtomicInteger cashTotal = new AtomicInteger();


        long create = 0;
        AtomicLong declined = new AtomicLong();
        AtomicLong pending = new AtomicLong();
        AtomicLong succeeded = new AtomicLong();


        AtomicLong economy = new AtomicLong();
        AtomicLong premiumEconomy = new AtomicLong();
        AtomicLong business = new AtomicLong();
        AtomicLong first = new AtomicLong();

        paymentsToday.forEach(paymentDto -> {
            totalPassengerToday.addAndGet(paymentDto.getTicket().size());

            amount.updateAndGet(v -> (v + paymentDto.getTotal()));

            if (paymentDto.getPaymentType() == PaymentType.CARD){
                cardTotal.getAndIncrement();
            }else{
                cashTotal.getAndIncrement();
            }

            if (paymentDto.getPaymentStatus() == PaymentStatus.DECLINED){
                declined.getAndIncrement();
            }
            else if (paymentDto.getPaymentStatus() == PaymentStatus.PENDING){
                pending.getAndIncrement();
            }
            else if (paymentDto.getPaymentStatus() == PaymentStatus.SUCCEEDED){
                succeeded.getAndIncrement();
            }

            for (TicketDto ticketDto : paymentDto.getTicket()) {
                switch (ticketDto.getType()){
                    case 0:
                        economy.getAndIncrement();
                        break;
                    case 1:
                        business.getAndIncrement();
                    case 2:
                        premiumEconomy.getAndIncrement();
                    case 3:
                        first.getAndIncrement();
                }
            }
        });

        final double cardPercent = (double) cardTotal.get() /(cardTotal.get()+cashTotal.get());
        final double cashPercent = (double) cashTotal.get() /(cardTotal.get()+cashTotal.get());

        final TotalStatisticalData totalData =
                TotalStatisticalData
                        .builder()
                        .totalCustomerToday(totalCustomerToday)
                        .totalPaymentToday(totalPaymentToday)
                        .totalFlightToday(totalFlightToday)
                        .totalPassengerToday(totalPassengerToday.get())
                        .build();

        final Revenue revenue = Revenue
                .builder()
                .amount(amount.get())
                .otherPercent(0)
                .cardPercent(cardPercent)
                .cashPercent(cashPercent)
                .build();

        final PaymentStatusStateData paymentStatusStateData = PaymentStatusStateData
                .builder()
                .create(create)
                .declined(declined.get())
                .pending(pending.get())
                .succeeded(succeeded.get())
                .build();

        final TicketTierData ticketTierData = TicketTierData
                .builder()
                .business(business.get())
                .economy(economy.get())
                .first(first.get())
                .premiumEconomy(premiumEconomy.get())
                .build();


        return PaymentManagementPageDto
                .builder()
                .payments(paymentsToday)
                .revenue(revenue)
                .statusData(paymentStatusStateData)
                .ticketTierData(ticketTierData)
                .totalData(totalData)
                .build();

    }

    @Override
    public List<PaymentDto> fetchAllPaymentByCreatedDate(long createdDate) {
        final Date date = new Date(createdDate);
        return paymentRepository.findByCreatedDate( new Date(createdDate)).stream().map(PaymentDto::fromEntity).toList();
    }


    @Override
    public List<PaymentDto> searchPaymentItem(String keyword) {
        return paymentRepository
                .findAll()
                .stream()
                .map(PaymentDto::fromEntity)
                .toList();
    }

    @Override
    public List<PaymentNoCustomerDto> getPaymentByCustomerId(long id) {
        try {
            return paymentRepository.findByCustomerId(id).stream().map(PaymentNoCustomerDto::fromEntity).toList();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public List<PaymentDto> filterPaymentList(PaymentFilter paymentFilter) {
        return paymentRepository.filterPayment(
                0,
                paymentRepository.count(),
                paymentFilter.getCreateDate(),
                paymentFilter.getPaymentStatus().toString(),
                paymentFilter.getPaymentType().toString()
        ).stream().map(PaymentDto::fromEntity)
                .toList();
    }

    @Override
    public PaymentDto getPaymentById(long id) {
        final Optional<PaymentEntity> optionalPaymentEntity = paymentRepository.findById(id);
        if (optionalPaymentEntity.isPresent()){
            return PaymentDto.fromEntity(optionalPaymentEntity.get());
        }
        throw new RuntimeException("Can not found any corresponding payment");
    }

    @Override
    public List<PaymentDto> getPaymentByPage(int page, int perPage) {
       try {
           PageRequest pageRequest = PageRequest.of(page, perPage);

           return paymentRepository
                   .findAll(pageRequest)
                   .stream()
                   .map(PaymentDto::fromEntity)
                   .toList();
       }
       catch (Exception e){
           throw new RuntimeException("Something wrong happened during paging process");
       }
    }

    @Override
    public PaymentDto updatePayment(long id, AddPaymentDto newPaymentDti) {
        final PaymentDto paymentDto = getPaymentById(id);

        try {
            final PaymentEntity paymentEntity = paymentRepository.save(
                    PaymentEntity
                        .builder()
                        .id(paymentDto.getId())
                        .status(newPaymentDti.getStatus())
                        .paymentType(newPaymentDti.getPaymentType())
//                        .customers(newPaymentDti.getCustomers())
                        .total(newPaymentDti.getTotal())
                        .createdDate(new Date(newPaymentDti.getCreatedDate()))
//                        .ticket(newPaymentDti.getTicket())
                        .build()
            );

            return PaymentDto.fromEntity(paymentEntity);
        }

        catch (Exception e){
            throw new RuntimeException("Can not update payment: "+ e.getMessage());
        }
    }

    @Override
    public boolean deletePayment(long id) {
        try {
            paymentRepository.deleteById(id);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

        return true;
    }
}
