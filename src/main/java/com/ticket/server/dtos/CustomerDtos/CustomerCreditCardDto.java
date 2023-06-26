package com.ticket.server.dtos.CustomerDtos;

import com.ticket.server.dtos.CreditCard.CreditCardDto;
import com.ticket.server.entities.CreditCardEntity;
import com.ticket.server.entities.CustomerEntity;
import com.ticket.server.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerCreditCardDto {

    private Long id;
    private String name;
    private String identifyNum;
    private long birthday;
    private String phone;
    private String email;
    private Gender gender;
    private CreditCardDto creditCard;

    public static CustomerCreditCardDto fromEntity(CustomerEntity entity){
        return CustomerCreditCardDto
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

    public CustomerEntity toEntity(){
        return CustomerEntity
                .builder()
                .id(getId())
                .name(getName())
                .identifyNum(getIdentifyNum())
                .birthday(getBirthday())
                .phone(getPhone())
                .email(getEmail())
                .gender(getGender())
                .creditCards(getCreditCard().toEntity())
                .build();
    }
}
