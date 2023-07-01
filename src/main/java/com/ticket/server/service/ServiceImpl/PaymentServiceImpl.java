package com.ticket.server.service.ServiceImpl;

import com.ticket.server.dtos.FlightDtos.FlightDto;
import com.ticket.server.dtos.Payment.*;
import com.ticket.server.dtos.Payment.PaymentManagementPage.*;
import com.ticket.server.dtos.TicketDtos.TicketDto;
import com.ticket.server.entities.CreditCardEntity;
import com.ticket.server.entities.CustomerEntity;
import com.ticket.server.entities.PaymentEntity;
import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.enums.PaymentType;
import com.ticket.server.exceptions.NotFoundException;
import com.ticket.server.repository.CreditCardRepository;
import com.ticket.server.repository.CustomerRepository;
import com.ticket.server.repository.FlightRepository;
import com.ticket.server.repository.PaymentRepository;
import com.ticket.server.service.IService.IFlightService;
import com.ticket.server.service.IService.IPaymentService;
import com.ticket.server.service.IService.ITicketService;
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
    private final CustomerRepository customerRepository;
    private final CreditCardRepository creditCardRepository;
    private final ITicketService ticketService;
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
    public Long getTotalPayment(){
        return paymentRepository.count();
    }

    @Override
    public List<PaymentDto> getPaymentByDateRange(long from, long to){
        return paymentRepository
                .getPaymentByDateRange(new Date(from),new Date(to))
                .stream()
                .map(PaymentDto::fromEntity)
                .toList();
    }


    @Override
    public PaymentManagementPageDto fetchPaymentManagementPage(int page,int perPage){
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

        for (PaymentDto paymentDto : paymentsToday) {
            totalPassengerToday.addAndGet(paymentDto.getTicket().size());

            amount.updateAndGet(v -> (v + paymentDto.getTotal()));

            if (paymentDto.getPaymentType() == PaymentType.CARD) {
                cardTotal.getAndIncrement();
            } else {
                cashTotal.getAndIncrement();
            }

            if (paymentDto.getPaymentStatus() == PaymentStatus.DECLINED) {
                declined.getAndIncrement();
            } else if (paymentDto.getPaymentStatus() == PaymentStatus.PENDING) {
                pending.getAndIncrement();
            } else if (paymentDto.getPaymentStatus() == PaymentStatus.SUCCEEDED) {
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
        }

        double cardPercent = 0;
        double cashPercent = 0;

        if (cardTotal.get()+cashTotal.get() != 0) {
            cardPercent = (double) cardTotal.get() / (cardTotal.get() + cashTotal.get());
            cashPercent = (double) cashTotal.get() / (cardTotal.get() + cashTotal.get());
        }

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
                .totalPayment(getTotalPayment())
                .payments(getPaymentByPage(page,perPage))
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
    public List<PaymentNoTicketCustomerDto> searchPaymentItem(String keyword) {
        return paymentRepository
                .searchPayment(keyword)
                .stream()
                .map(PaymentNoTicketCustomerDto::fromEntity)
                .toList();
    }

    @Override
    public List<PaymentNoTicketCustomerDto> getPaymentByCustomerId(long id) {
        try {
            return paymentRepository.findByCustomerId(id).stream().map(PaymentNoTicketCustomerDto::fromEntity).toList();
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
                paymentFilter.getPaymentType().toString())
                .stream()
                .map(PaymentDto::fromEntity)
                    .toList();
    }

    @Override
    public PaymentEntity getPaymentById(long id) {
        final Optional<PaymentEntity> optionalPaymentEntity = paymentRepository.findById(id);
        if (optionalPaymentEntity.isPresent()){
            return (optionalPaymentEntity.get());
        }
        throw new RuntimeException("Can not found any corresponding payment");
    }

    @Override
    public List<PaymentNoTicketCustomerDto> getPaymentByPage(int page, int perPage) {
       try {
           PageRequest pageRequest = PageRequest.of(page, perPage);
           return paymentRepository
                   .findAll(pageRequest).getContent()
                   .stream()
                   .map(PaymentNoTicketCustomerDto::fromEntity)
                   .toList();
       }
       catch (Exception e){
           throw new RuntimeException("Something wrong happened during paging process");
       }
    }

    @Override
    public PaymentDtoDetail updatePayment(long id, PaymentDtoDetail newPaymentDti) {
        final PaymentEntity oldPayment = getPaymentById(id);

        System.out.println(newPaymentDti.getCustomer().getCreditCard().getId());

        try {
            final CustomerEntity newCustomer = newPaymentDti.getCustomer().toEntity();
            final CreditCardEntity newCreditCardEntity = newPaymentDti.getCustomer().getCreditCard().toEntity();

            newCreditCardEntity.setCustomer(newCustomer);
            newCustomer.setCreditCards(newCreditCardEntity);

            creditCardRepository.save(newCreditCardEntity);

            final CustomerEntity newCustomerEntity=  customerRepository.save(newCustomer);

            final PaymentEntity paymentEntity = paymentRepository.save(
                    PaymentEntity
                        .builder()
                        .id(oldPayment.getId())
                        .status(newPaymentDti.getPaymentStatus())
                        .paymentType(newPaymentDti.getPaymentType())
                        .customers(newCustomerEntity)
                        .total(newPaymentDti.getTotal())
                        .createdDate(new Date(newPaymentDti.getCreatedDate()))
                        .flight(oldPayment.getFlight())
                        .ticket(oldPayment.getTicket())
                        .build()
            );

            return PaymentDtoDetail.fromEntity(paymentEntity);
        }

        catch (Exception e){
            throw new RuntimeException("Can not update payment: "+ e.getMessage());
        }
    }

    @Override
    public boolean deletePayment(long id) {
        try {
            final Optional<PaymentEntity> optionalPaymentEntity = paymentRepository.findById(id);

            if (optionalPaymentEntity.isPresent()) {
                final PaymentEntity paymentEntity = optionalPaymentEntity.get();

                paymentEntity.getTicket().forEach(ticketEntity -> {
                    ticketService.deleteTicket(ticketEntity.getId());
                });

                paymentRepository.deleteById(id);
                return true;
            }
            return false;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
