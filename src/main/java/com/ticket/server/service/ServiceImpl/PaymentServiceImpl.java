package com.ticket.server.service.ServiceImpl;

import com.ticket.server.dtos.Payment.AddPaymentDto;
import com.ticket.server.dtos.Payment.PaymentDto;
import com.ticket.server.dtos.Payment.PaymentFilter;
import com.ticket.server.entities.PaymentEntity;
import com.ticket.server.enums.PaymentStatus;
import com.ticket.server.repository.PaymentRepository;
import com.ticket.server.service.IService.IPaymentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public List<PaymentDto> fetchPaymentData() {;
        return paymentRepository
                .findAll()
                .stream()
                .map(PaymentDto::fromEntity)
                .toList();
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
                        .customers(newPaymentDti.getCustomers())
                        .total(newPaymentDti.getTotal())
                        .createdDate(newPaymentDti.getCreatedDate())
                        .ticket(newPaymentDti.getTicket())
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
