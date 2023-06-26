package com.ticket.server.dtos.CreditCard;

import com.ticket.server.entities.CreditCardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreditCardDto {
    private Long id;
    private String creditNum;
    private Long expiredDate;
    private String cvc;
    private String nameCard;

    static public CreditCardDto fromEntity(CreditCardEntity entity)
    {
        return CreditCardDto
                 .builder()
                 .id(entity.getId())
                 .creditNum(entity.getCreditNum())
                 .cvc(entity.getCvc())
                 .nameCard(entity.getNameCard())
                 .build();
    }
}
