package com.ticket.server.dtos.CustomerDtos;

import com.ticket.server.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddCustomerRequest {
    private String name;
    private String identifyNum;
    private Long birthday;
    private String phone;
    private String email;
    private Gender gender;
}
