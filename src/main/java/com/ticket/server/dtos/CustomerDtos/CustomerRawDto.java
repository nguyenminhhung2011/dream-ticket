package com.ticket.server.dtos.CustomerDtos;

import com.ticket.server.dtos.CreditCard.CreditCardDto;
import com.ticket.server.entities.CustomerEntity;
import com.ticket.server.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRawDto {
    private Long id;
    private String name;
    private String identifyNum;
    private Long birthday;
    private String phone;
    private String email;
    private Gender gender;
    private CreditCardDto creditCard;

    public static CustomerRawDto fromEntity(CustomerEntity entity){
        return CustomerRawDto
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .identifyNum(entity.getIdentifyNum())
                .birthday(entity.getBirthday())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .gender(entity.getGender())
                .creditCard(CreditCardDto.fromEntity(entity.getCreditCards()))
                .build();
    }
}
