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

    static public CreditCardDto fromEntity(CreditCardEntity entity) {
        if (entity != null) {
            return CreditCardDto
                    .builder()
                    .id(entity.getId())
                    .creditNum(entity.getCreditNum())
                    .cvc(entity.getCvc())
                    .expiredDate(entity.getExpiredDate())
                    .nameCard(entity.getNameCard())
                    .build();
        }
        return null;
    }

    public CreditCardEntity toEntity(){
        return CreditCardEntity
                .builder()
                .id(getId())
                .expiredDate(getExpiredDate())
                .creditNum(getCreditNum())
                .cvc(getCvc())
                .nameCard(getNameCard())
                .build();
    }
}
