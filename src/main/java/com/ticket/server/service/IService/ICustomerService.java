package com.ticket.server.service.IService;

import com.ticket.server.dtos.CustomerDtos.AddCustomerRequest;
import com.ticket.server.dtos.CustomerDtos.CustomerRawDto;

import java.util.List;

public interface ICustomerService {
    List<CustomerRawDto> getAllCustomer();

    CustomerRawDto getCustomerById(long id);

    List<CustomerRawDto> getCustomerByPage();

    CustomerRawDto addNewCustomer(AddCustomerRequest customer);

    boolean updateNewCustomer(CustomerRawDto customer );

    boolean deleteCustomer(long id);

}
