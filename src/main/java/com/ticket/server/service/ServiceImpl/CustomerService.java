package com.ticket.server.service.ServiceImpl;

import com.ticket.server.dtos.CustomerDtos.AddCustomerRequest;
import com.ticket.server.dtos.CustomerDtos.CustomerRawDto;
import com.ticket.server.entities.CustomerEntity;
import com.ticket.server.repository.CustomerRepository;
import com.ticket.server.service.IService.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerRawDto> getAllCustomer() {
        return customerRepository.findAll().stream().map(CustomerRawDto::fromEntity).toList();
    }

    @Override
    public CustomerRawDto getCustomerById(long id) {
        final Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);

        if (customerEntityOptional.isPresent()){
            return CustomerRawDto.fromEntity(customerEntityOptional.get());
        }
        else {
            throw new RuntimeException("Can not found customer");
        }
    }

    @Override
    public List<CustomerRawDto> getCustomerByPage() {
        return null;
    }

    @Override
    public List<CustomerRawDto> searchCustomer(String keyword) {
        return customerRepository.searchCustomer(keyword).stream().map(CustomerRawDto::fromEntity).toList();
    }

    @Override
    public CustomerRawDto addNewCustomer(AddCustomerRequest request) {
        try {
            final CustomerEntity customerEntity = CustomerEntity
                    .builder()
                    .name(request.getName())
                    .phone(request.getPhone())
                    .identifyNum(request.getIdentifyNum())
                    .email(request.getEmail())
                    .birthday(request.getBirthday())
                    .gender(request.getGender())
                    .build();

            final CustomerEntity savedCustomer = customerRepository.save(customerEntity);

            return CustomerRawDto.fromEntity(savedCustomer);

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public CustomerRawDto updateNewCustomer(CustomerRawDto customer) {
        final Optional<CustomerEntity> customerEntity = customerRepository.findById(customer.getId());
        if (customerEntity.isPresent()){
            final CustomerEntity oldCustomer = customerEntity.get();

            final CustomerEntity newCustomer = CustomerEntity
                    .builder()
                    .id(oldCustomer.getId())
                    .payments(oldCustomer.getPayments())
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .phone(customer.getPhone())
                    .gender(customer.getGender())
                    .birthday(customer.getBirthday())
                    .identifyNum(customer.getIdentifyNum())
                    .build();

            final  CustomerEntity savedCustomer = customerRepository.save(newCustomer);

            return CustomerRawDto.fromEntity(savedCustomer);
        }else{
            throw new RuntimeException("Can not found any customer corresponding");
        }
    }

    @Override
    public boolean deleteCustomer(long id) {
        if (customerRepository.existsById(id)){
            customerRepository.deleteById(id);
            return true;
        }
        else {
            throw new RuntimeException("Can not found customer");
        }
    }
}
